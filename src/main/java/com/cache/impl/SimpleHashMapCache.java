package com.cache.impl;

import com.cache.core.Cache;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleHashMapCache<K, V> implements Cache<K, V> {

 private final Map<K, V> storage;

 public SimpleHashMapCache() {
  this.storage = new HashMap<>();
 }

 @Override
 public Optional<V> get(K key) {
  V value = storage.get(key);
  return value != null ? Optional.of(value) : Optional.empty();
 }

 @Override
 public void put(K key, V value) {
  storage.put(key, value);
 }

 @Override
 public boolean remove(K key) {
  return storage.remove(key) != null;
 }

 @Override
 public int size() {
  return storage.size();
 }

 @Override
 public void clear() {
  storage.clear();
 }

 @Override
 public boolean containsKey(K key) {
  return storage.containsKey(key);
 }
}