package datastructure;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    public static class LRUCacheImplementation<K, V> {

        private final int CACHE_SIZE;
        private Deque<K> deque;
        private Map<K, V> map;

        public LRUCacheImplementation(int capacity) {
            CACHE_SIZE = capacity;
            deque = new LinkedList<>();
            map = new HashMap<>();
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                V current = map.get(key);
                deque.remove(key);
                deque.add(key);
                return current;
            }
            return null;
        }

        public void put(K key, V value) {
            if (map.containsKey(key)) {
                return;
            }

            if (CACHE_SIZE == deque.size()) {
                K removedKey = deque.poll();
                map.remove(removedKey);
            }

            map.put(key, value);
            deque.add(key);
        }
    }

    public static void main(String[] args) {
        LRUCacheImplementation<Integer, Integer> LRUCache = new LRUCacheImplementation<>(2);
        LRUCache.put(1, 1);
        LRUCache.put(2, 2);
        LRUCache.get(1);
        LRUCache.put(3, 3);
        LRUCache.get(2);
        LRUCache.put(4, 4);
        LRUCache.get(1);
        LRUCache.get(3);
    }
}
