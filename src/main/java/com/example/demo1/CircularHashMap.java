package com.example.demo1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// This class is used to make the waiting list circular and to add and remove new items

public class CircularHashMap<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private Iterator<K> keyIterator;
    private K currentKey;

    public void put(K key, V value) {
        map.put(key, value);
        if (currentKey == null) {
            currentKey = key;
        }
    }

    public V get(K key) {
        return map.get(key);
    }

    public Map.Entry<K, V> next() {
        if (keyIterator == null || !keyIterator.hasNext()) {
            keyIterator = map.keySet().iterator();
        }
        currentKey = keyIterator.next();
        V value = map.get(currentKey);
        return new HashMap.SimpleEntry<>(currentKey, value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void removeFirst() {
        if (currentKey != null) {
            map.remove(currentKey);
            keyIterator = null; // Reset the iterator to ensure consistency
            if (map.keySet().iterator().hasNext()) {
                currentKey = map.keySet().iterator().next();
            } else {
                currentKey = null;
            }
        }
    }
}
