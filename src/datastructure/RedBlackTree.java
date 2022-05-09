package datastructure;

public class RedBlackTree {

    private static class RedBlackTreeImplementation {

        private Node root;

        private static class Node {
            private Node left, right;
            private int key, size;
            private boolean red;

            public Node(int key, boolean red, int size) {
                this.key = key;
                this.red = red;
                this.size = size;
            }
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }
            return node.size;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            y.red = x.red;
            x.red = true;
            y.size = x.size;
            x.size = size(x.left) + size(x.right) + 1;

            return y;
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            y.left = x.right;
            x.right = y;
            x.red = y.red;
            x.size = y.size;
            y.size = size(y.left) + size(y.right) + 1;

            return x;
        }

        private boolean isRed(Node node) {
            if (node == null) {
                return false;
            }
            return node.red;
        }

        private void flipColors(Node node) {
            node.red = true;
            node.left.red = false;
            node.right.red = false;
        }

        public void insert(int key) {
            root = insert(key, root);
        }

        private Node insert(int key, Node node) {
            if (node == null) {
                return new Node(key, true, 1);
            }
            if (key < node.key) {
                node.left = insert(key, node.left);
            } else if (key > node.key) {
                node.right = insert(key, node.right);
            } else {
                // Duplicate keys not allowed
                return node;
            }

            if (isRed(node.right) && !isRed(node.left)) {
                return leftRotate(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                return rightRotate(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }

            node.size = size(node.left) + size(node.right) + 1;
            return node;
        }

        public void delete(int key) {
            if (!isRed(root.left) && !isRed(root.right)) {
                root.red = true;
            }
            root = delete(key, root);
            if (root.size != 0) root.red = false;
        }

        private Node delete(int key, Node node) {
            if (key < node.key) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = delete(key, node.left);
            } else {
                if (isRed(node.left)) {
                    node = rightRotate(node);
                }
                if (key == node.key && node.right == null) {
                    return null;
                }
                if (!isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (key == node.key) {
                    node.key = min(node.right).key;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = delete(key, node.right);
                }
            }
            return balance(node);
        }

        private Node balance(Node node) {
            if (isRed(node.right) && !isRed(node.left))    node = leftRotate(node);
            if (isRed(node.left) && isRed(node.left.left)) node = rightRotate(node);
            if (isRed(node.left) && isRed(node.right))     flipColors(node);
            node.size = size(node.left) + size(node.right) + 1;

            return node;
        }

        private Node min(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private Node moveRedRight(Node node) {
            flipColors(node);
            if (!isRed(node.left.left))
                node = rightRotate(node);
            return node;
        }

        private Node moveRedLeft(Node node) {
            flipColors(node);
            if (isRed(node.right.left)) {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
            return node;
        }

        public void deleteMin() {
            if (!isRed(root.left) && !isRed(root.right)) {
                root.red =true;
            }
            root = deleteMin(root);
            if (root.size != 0) {
                root.red = false;
            }
        }

        private Node deleteMin(Node node) {
            if (node.left == null) {
                return null;
            }
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = deleteMin(node.left);
            return balance(node);
        }

        public void printInOrder() {
            printInOrder(root);
        }

        private void printInOrder(Node node) {
            if (node.left != null) {
                printInOrder(node.left);
            }
            System.out.println(node.key);
            if (node.right != null) {
                printInOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        RedBlackTreeImplementation tree = new RedBlackTreeImplementation();
        tree.insert(7);
        tree.insert(3);
        tree.insert(18);
        tree.insert(10);
        tree.insert(22);
        tree.insert(8);
        tree.insert(11);
        tree.insert(26);
        tree.insert(2);
        tree.insert(6);
        tree.insert(13);
        tree.printInOrder();
    }
}
