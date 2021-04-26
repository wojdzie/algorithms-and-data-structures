package algorithm;

public class BinarySearch<K extends Comparable<K>, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public BinarySearch(int capacity) {
        this.keys = (K[]) new Comparable[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    private int rank(K key) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (key.compareTo(keys[mid]) == 0) {
                return mid;
            } else if (key.compareTo(keys[mid]) > 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public void put(K key, V value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
