package com.cache.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleHashMapCacheTest {

 private SimpleHashMapCache<String, String> cache;

 @BeforeEach
 void setUp() {
  cache = new SimpleHashMapCache<>();
 }

 @Test
 void testPutAndGet() {
  // Put data in cache
  cache.put("key1", "value1");

  // Get data from cache
  Optional<String> result = cache.get("key1");

  // Check if data exists and matches
  assertTrue(result.isPresent());
  assertEquals("value1", result.get());
 }

 @Test
 void testGetNonExistentKey() {
  // Try to get key that doesn't exist
  Optional<String> result = cache.get("nonexistent");

  // Should be empty
  assertFalse(result.isPresent());
 }

 @Test
 void testSize() {
  assertEquals(0, cache.size());

  cache.put("key1", "value1");
  assertEquals(1, cache.size());

  cache.put("key2", "value2");
  assertEquals(2, cache.size());
 }
}