package com.cache.impl;
import com.cache.core.Cache;
import java.util.Optional;
import java.util.HashMap;
public class LruCache<K, V> implements Cache<K, V> {
 private final int capacity;
 private final HashMap<K, Node> cache;
 private final DoublyLinkedList usageOrder;
 public LruCache(int capacity) {
  this.capacity = capacity;
  this.cache = new HashMap<>();
  this.usageOrder = new DoublyLinkedList();
 }
 @Override
 public Optional<V> get(K key) {
  if (!cache.containsKey(key)) {
   return Optional.empty();
  }
  Node node = cache.get(key);
  usageOrder.moveToFront(node);
  return Optional.of(node.value);
 }
 @Override
 public void put(K key, V value) {
  if (cache.containsKey(key)) {
   Node node = cache.get(key);
   node.value = value;
   usageOrder.moveToFront(node);
  } else {
   if (cache.size() >= capacity) {
    K lruKey = usageOrder.removeLast();
    cache.remove(lruKey);
   }
   Node newNode = new Node(key, value);
   cache.put(key, newNode);
   usageOrder.addToFront(newNode);
  }
 }

 @Override
 public boolean remove(K key) {
  if (!cache.containsKey(key)) {
   return false;
  }
  Node node = cache.get(key);
  usageOrder.remove(node);
  cache.remove(key);
  return true;
 }

 @Override
 public int size() {
  return cache.size();
 }

 @Override
 public void clear() {
  cache.clear();
  usageOrder.clear();
 }

 @Override
 public boolean containsKey(K key) {
  return cache.containsKey(key);
 }

 private class Node {
  K key;
  V value;
  Node prev;
  Node next;

  Node(K key, V value) {
   this.key = key;
   this.value = value;
  }
 }

 private class DoublyLinkedList {
  private Node head;
  private Node tail;

  void addToFront(Node node) {
   if (head == null) {
    head = tail = node;
    node.prev = null;
    node.next = null;
   } else {
    node.next = head;
    head.prev = node;
    head = node;
    node.prev = null;
   }
  }
  void remove(Node node) {
   if (node.prev != null) {
    node.prev.next = node.next;
   } else {
    head = node.next;
   }

   if (node.next != null) {
    node.next.prev = node.prev;
   } else {
    tail = node.prev;
   }
  }

  void moveToFront(Node node) {
   if (node.prev == null) return; // Already at front
   remove(node);
   addToFront(node);
  }
  K removeLast() {
   if (tail == null) return null;
   K key = tail.key;
   if (head == tail) {
    head = tail = null;
   } else {
    tail = tail.prev;
    tail.next = null;
   }
   return key;
  }
  void clear() {
   head = tail = null;
  }
 }
}