package com.cache;

import com.cache.impl.SimpleHashMapCache;
import java.util.Optional;

public class CacheDemo {
 public static void main(String[] args) {
  System.out.println("=== Cache Demo Started ===");

  SimpleHashMapCache<String, String> cache = new SimpleHashMapCache<>();

  // Test basic operations
  System.out.println("Initial cache size: " + cache.size());

  // Add some data
  cache.put("user1", "John Doe");
  cache.put("user2", "Jane Smith");
  cache.put("product1", "Laptop");

  System.out.println("After adding 3 items, size: " + cache.size());

  // Retrieve data
  Optional<String> user1 = cache.get("user1");
  if (user1.isPresent()) {
   System.out.println("Found user1: " + user1.get());
  }

  // Test non-existent key
  Optional<String> user3 = cache.get("user3");
  if (user3.isEmpty()) {
   System.out.println("user3 not found (as expected)");
  }

  // Test removal
  boolean removed = cache.remove("user2");
  System.out.println("Removed user2: " + removed);
  System.out.println("Final cache size: " + cache.size());

  System.out.println("=== Cache Demo Completed ===");
 }
}