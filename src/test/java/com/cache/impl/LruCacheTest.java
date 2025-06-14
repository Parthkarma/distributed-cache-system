package com.cache.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

 private LruCache<String, String> cache;

 @BeforeEach
 void setUp() {
  // Initialize cache with capacity of 2 for testing eviction
  cache = new LruCache<>(2);

 }

 @Test
 void testBasicGetAndPut() {
  cache.put("key1", "value1");
  cache.put("key2", "value2");

  assertEquals("value1", cache.get("key1").get());
  assertEquals("value2", cache.get("key2").get());
 }

 @Test
 void testEvictionPolicy() {
  // Add two items
  cache.put("key1", "value1");
  cache.put("key2", "value2");

  // Access key1 (should become MRU)
  cache.get("key1");

  // Add third item → should evict key2 (LRU)
  cache.put("key3", "value3");

  // key1 should still be present
  assertTrue(cache.get("key1").isPresent());

  // key2 should be evicted
  assertFalse(cache.get("key2").isPresent());

  // key3 should be present
  assertTrue(cache.get("key3").isPresent());
 }

 @Test
 void testUpdateExistingKeyDoesNotEvict() {
  cache.put("key1", "value1");
  cache.put("key2", "value2");

  // Update existing key
  cache.put("key1", "new-value");

  // Ensure nothing was evicted
  assertTrue(cache.get("key2").isPresent());
  assertEquals("new-value", cache.get("key1").get());
 }

 @Test
 void testRemove() {
  cache.put("key1", "value1");
  assertTrue(cache.remove("key1"));
  assertFalse(cache.remove("key1")); // Should return false if not found
 }
 @Test
 void testSizeAndContainsKey() {
  cache.put("key1", "value1");
  assertEquals(1, cache.size());
  assertTrue(cache.containsKey("key1"));

  cache.put("key2", "value2");
  assertEquals(2, cache.size());

  cache.put("key3", "value3"); // Should evict one item
  assertEquals(2, cache.size());

  // After eviction, key1 may or may not exist depending on access order
  // But key3 must exist
  assertTrue(cache.containsKey("key3"));
 }

 @Test
 void testGetFromEmptyCacheReturnsEmptyOptional() {
  Optional<String> result = cache.get("nonexistent");
  assertFalse(result.isPresent());
 }
}