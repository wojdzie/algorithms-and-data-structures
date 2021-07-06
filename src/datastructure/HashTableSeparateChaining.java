package datastructure;

import java.util.LinkedList;
import java.util.List;

public class HashTableSeparateChaining<K, V> {

    private int size;
    private List<Node>[] objects;

    private class Node {
        K key;
        V value;
    }

    public HashTableSeparateChaining(int size) {
        this.size = size;
        objects = (LinkedList<Node>[]) new LinkedList[size];
        for (int i = 0; i < size; i++) {
            objects[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    public V get(K key) {
        int hash = hash(key);
        List<Node> objects = this.objects[hash];
        for (Node object : objects) {
            if (object.key == key) {
                return object.value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        List<Node> objects = this.objects[hash];
        Node node = new Node();
        node.key = key;
        node.value = value;

        objects.add(node);
    }
}
