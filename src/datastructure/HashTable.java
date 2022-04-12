package datastructure;


import java.util.LinkedList;
import java.util.List;

public class HashTable {

    private static class HashTableWithSeparateChainingImplementation<K, V> {

        private static final double THRESHOLD = 0.7;

        private int M; // Number of slots in hash table
        private int N; // Number of keys to be inserted in hash table
        private List<HashNode<K, V>>[] bucketArray;

        private class HashNode<K, V> {
            K key;
            V value;

            public HashNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        public HashTableWithSeparateChainingImplementation(int M) {
            this.bucketArray = new List[M];
            this.M = M;
            this.N = 0;
            for (int i = 0; i < M; i++) {
                bucketArray[i] = new LinkedList<>();
            }
        }

        public V get(K key) {
            int hash = hash(key);
            List<HashNode<K, V>> bucket = bucketArray[hash];
            for (HashNode<K, V> node : bucket) {
                if (key.equals(node.key)) {
                    return node.value;
                }
            }
            return null;
        }

        private int hash(K key) {
            return (key.hashCode() & Integer.MAX_VALUE) % M;
        }

        private void resize(int capacity) {
            List<HashNode<K, V>>[] oldBucketArray = this.bucketArray;
            List<HashNode<K, V>>[] newBucketArray = new List[capacity];
            for (int i = 0; i < M * 2; i++) {
                newBucketArray[i] = new LinkedList<>();
            }
            this.N = 0;
            this.M = capacity;
            this.bucketArray = newBucketArray;
            for (int i = 0; i < M / 2; i++) {
                List<HashNode<K, V>> bucket = oldBucketArray[i];
                for (HashNode<K, V> node : bucket) {
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            if (get(key) == null) {
                if (getLoadFactor() >= THRESHOLD) {
                    resize(M * 2);
                }
                int hash = hash(key);
                List<HashNode<K, V>> bucket = bucketArray[hash];
                bucket.add(new HashNode<>(key, value));
                N++;
            }
        }

        private double getLoadFactor() {
            return 1.0 * N / M;
        }

        public void remove(K key) {
            int hash = hash(key);
            List<HashNode<K, V>> bucket = bucketArray[hash];
            for (HashNode<K, V> pair : bucket) {
                if (key.equals(pair.key)) {
                    bucket.remove(pair);
                    N--;
                    return;
                }
            }
        }
    }

    private static class HashTableWithLinearProbingImplementation<K, V> {

        private int M; // Number of slots in hash table
        private int N; // Number of keys to be inserted in hash table
        private K[] keys;
        private V[] values;

        public HashTableWithLinearProbingImplementation(int M) {
            keys = (K[]) new Object[M];
            values = (V[]) new Object[M];
            this.M = M;
        }

        private int hash(K key) {
            return (key.hashCode() & Integer.MAX_VALUE) % M;
        }

        public V get(K key) {
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key)) {
                    return values[i];
                }
            }
            return null;
        }

        private void resize(int capacity) {
            HashTableWithLinearProbingImplementation<Object, Object> newHashTable = new HashTableWithLinearProbingImplementation<>(capacity);
            for (int i = 0; i < M; i++) {
                if (keys[i] != null) {
                    newHashTable.put(keys[i], values[i]);
                }
            }
            keys = (K[]) newHashTable.keys;
            values = (V[]) newHashTable.values;
            this.M = newHashTable.M;
        }

        public void put(K key, V value) {
            if (N >= (M / 2)) {
                resize(M * 2);
            }
            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key)) {
                    values[i] = value;
                    return;
                }
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }

        public void remove(K key) {
            if (get(key) == null) {
                return;
            }
            int i = hash(key);
            while (!key.equals(keys[i])) {
                i = (i + 1) % M;
            }
            keys[i] = null;
            values[i] = null;

            i = (i + 1) % M;
            while (keys[i] != null) {
                K keyToRedo = keys[i];
                V valueToRedo = values[i];
                keys[i] = null;
                values[i] = null;
                N--;
                put(keyToRedo, valueToRedo);
                i = (i + 1) % M;
            }
            N--;
            if (N > 0 && 8 * N == M) {
                resize(M / 2);
            }
        }
    }

    public static void main(String[] args) {
        HashTableWithSeparateChainingImplementation<Integer, String> hashTableWithSeparateChainingImplementation = new HashTableWithSeparateChainingImplementation<>(4);
        hashTableWithSeparateChainingImplementation.put(251, "Two Five One");
        hashTableWithSeparateChainingImplementation.put(342, "Three Four Two");
        hashTableWithSeparateChainingImplementation.put(142, "One Four Two");
        hashTableWithSeparateChainingImplementation.put(121, "One Two One");
        hashTableWithSeparateChainingImplementation.put(433, "Four Three Three");
        hashTableWithSeparateChainingImplementation.remove(142);

        HashTableWithLinearProbingImplementation<Integer, String> hashTableWithLinearProbingImplementation = new HashTableWithLinearProbingImplementation<>(4);
        hashTableWithLinearProbingImplementation.put(251, "Two Five One");
        hashTableWithLinearProbingImplementation.put(342, "Three Four Two");
        hashTableWithLinearProbingImplementation.put(142, "One Four Two");
        hashTableWithLinearProbingImplementation.put(121, "One Two One");
        hashTableWithLinearProbingImplementation.put(433, "Four Three Three");
        hashTableWithLinearProbingImplementation.remove(433);
    }
}
