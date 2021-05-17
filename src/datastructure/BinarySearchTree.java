package datastructure;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node.size;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result > 0) {
            return get(node.right, key);
        } else if (result < 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int result = key.compareTo(node.key);
        if (result > 0) {
            node.right = put(node, key, value);
        } else if (result < 0) {
            node.left = put(node, key, value);
        } else {
            node.value = value;
        }
        node.size = node.left.size + node.right.size + 1;
        return node;
    }

    public K min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public K floor(K key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, K key) {
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result == 0) {
            return node;
        } else if (result < 0) {
            return floor(node.left, key);
        }
        Node tmp = floor(node.right, key);
        if (tmp != null) {
            return tmp;
        } else {
            return node;
        }
    }

    public K select(int position) {
        return select(root, position).key;
    }

    private Node select(Node node, int position) {
        if (node == null) {
            return null;
        }
        int size = size(node);
        if (size > position) {
            return select(node.left, position);
        } else if (size < position) {
            return select(node.right, position - size - 1);
        } else {
            return node;
        }
    }

    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(Node node, K key) {
        if (node == null) {
            return 0;
        }
        int result = key.compareTo(node.key);
        if (result > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else if (result < 0) {
            return rank(node.left, key);
        } else {
            return size(node.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = node.left.size + node.right.size + 1;
        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result > 0) {
            node.right = delete(node.right, key);
        } else if (result < 0) {
            node.left = delete(node.left, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = node.left.size + node.right.size + 1;
        return node;
    }
}
