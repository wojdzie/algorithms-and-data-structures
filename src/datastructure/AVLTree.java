package datastructure;

public class AVLTree {

    private static class AVLTreeImplementation {

        private Node root;

        private static class Node {
            private Node left, right;
            private int key, height;

            public Node(int key) {
                this.key = key;
                this.height = 1;
            }
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }
            return node.height;
        }

        private int max(int a, int b) {
            return Math.max(a, b);
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;

            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;

            return y;
        }

        private int getBalance(Node node) {
            if (node == null) {
                return 0;
            }
            return height(node.left) - height(node.right);
        }
        public void insert(int key) {
            root = insert(key, root);
        }

        private Node insert(int key, Node node) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(key, node.left);
            } else if (key > node.key) {
                node.right = insert(key, node.right);
            } else {
                // Duplicate keys not allowed
                return node;
            }

            node.height = max(height(node.left), height(node.right)) + 1;

            int balance = getBalance(node);
            // left-left case
            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }
            // right-right case
            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }
            // left-right case
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // right-left case
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        public void delete(int key) {
            root = delete(key, root);
        }

        private Node delete(int key, Node node) {
            if (node == null) {
                return null;
            }
            if (key < node.key) {
                node.left = delete(key, node.left);
            } else if (key > node.key) {
                node.right = delete(key, node.right);
            } else {
                // node with only one child or no child
                if (node.left == null || node.right == null) {
                    Node temp = null;
                    if (node.left == null) {
                        temp = node.right;
                    } else {
                        temp = node.left;
                    }
                    if (temp == null) {
                        temp = node;
                        node = null;
                    } else {
                        node = temp;
                    }
                // node with two children
                } else {
                    Node temp = minValue(node.right);
                    node.key = temp.key;
                    node.right = delete(key, node.right);
                }
            }

            if (node == null) {
                return null;
            }

            node.height = max(height(node.left), height(node.right)) + 1;

            int balance = getBalance(node);
            // left-left case
            if (balance > 1 && getBalance(node.left) >= 0) {
                return rightRotate(node);
            }
            // left-right case
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // right-right case
            if (balance < -1 && getBalance(node.right) <= 0) {
                return leftRotate(node);
            }
            // right-left case
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return node;
        }

        private Node minValue(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
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
        AVLTreeImplementation tree = new AVLTreeImplementation();
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);
        tree.delete(10);
        tree.printInOrder();
    }
}
