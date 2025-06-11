package com.cache.core;

import java.util.Optional;

public interface Cache<K, V> {

 // Get value by key
 Optional<V> get(K key);

 // Store key-value pair
 void put(K key, V value);

 // Remove a key
 boolean remove(K key);

 // Get cache size
 int size();

 // Clear all data
 void clear();

 // Check if key exists
 boolean containsKey(K key);
}