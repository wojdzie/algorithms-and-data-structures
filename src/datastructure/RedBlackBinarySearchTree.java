package datastructure;

public class RedBlackBinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean red;
        int size;

        public Node(K key, V value, boolean red, int size) {
            this.key = key;
            this.value = value;
            this.red = red;
            this.size = size;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }

        return node.red;
    }

    private Node rotateLeft(Node node) {
        Node target = node.right;
        node.right = target.right;
        target.left = node;

        target.red = node.red;
        node.red = true;
        target.size = node.size;
        node.size = node.left.size + node.right.size + 1;

        return target;
    }

    private Node rotateRight(Node node) {
        Node target = node.left;
        node.left = target.left;
        target.right = node;
        target.red = node.red;
        node.red = true;
        target.size = node.size;
        node.size = node.left.size + node.right.size + 1;

        return target;
    }

    private void flipColors(Node node) {
        node.red = true;
        node.left.red = false;
        node.right.red = false;
    }

    private int size(Node node) {
        return node.size;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, true, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.size = node.left.size + node.right.size + 1;
        return node;
    }
}
