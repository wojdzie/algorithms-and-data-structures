package datastructure;

public class BinarySearchTree {

    private static class BinarySearchTreeImplementation {

        private Node root;

        private static class Node {

            private Node left, right;
            private int key;

            public Node(int key) {
                this.key = key;
            }
        }

        public void insert(int key) {
            root = insert(key, root);
        }

        private Node insert(int key, Node root) {
            if (root == null) {
                root = new Node(key);
                return root;
            }
            if (key < root.key) {
                root.left = insert(key, root.left);
            } else if (key > root.key) {
                root.right = insert(key, root.right);
            }
            return root;
        }

        public boolean contains(int key) {
            return contains(key, root);
        }

        private boolean contains(int key, Node node) {
            if (key == node.key) {
                return true;
            } else if (key < node.key) {
                if (node.left == null) {
                    return false;
                }
                return contains(key, node.left);
            } else {
                if (node.right == null) {
                    return false;
                }
                return contains(key, node.right);
            }
        }

        public void delete(int key) {
            root = delete(key, root);
        }

        private Node delete(int key, Node node) {
            if (root == null) {
                return null;
            }
            if (key < node.key) {
                node.left = delete(key, node.left);
            } else if (key > node.key) {
                node.right = delete(key, node.right);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
                node.key = minValue(node.right);
                node.right = delete(node.key, node.right);
            }
            return node;
        }

        private int minValue(Node node) {
            int min = node.key;
            while (node.left != null) {
                min = node.left.key;
                node = node.left;
            }
            return min;
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
        BinarySearchTreeImplementation tree = new BinarySearchTreeImplementation();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.delete(20);
        tree.printInOrder();

    }
}
