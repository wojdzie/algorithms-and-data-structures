package datastructure;

public class HashTableLinearProbing<K, V> {

    private int occupied;
    private int size;
    private K[] keys;
    private V[] values;

    public HashTableLinearProbing(int size) {
        this.size = size;
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    public void put(K key, V value) {
        if (occupied >= size / 2) {
            resize(2 * size);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % size) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        occupied++;
    }

    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % size) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    private void resize(int size) {
        HashTableLinearProbing<K, V> hashTable = new HashTableLinearProbing<>(size);
        for (int i = 0; i < size; i++) {
            if (keys[i] != null) {
                hashTable.put(keys[i], values[i]);
            }
        }
        this.keys = hashTable.keys;
        this.values = hashTable.values;
        this.size = hashTable.size;
    }
}
