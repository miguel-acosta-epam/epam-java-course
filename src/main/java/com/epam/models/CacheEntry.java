package com.epam.models;

public class CacheEntry<K, V> {
  private final K key;
  private final V value;
  private long accessTime;

  public CacheEntry(K key, V value) {
    this.key = key;
    this.value = value;
    this.accessTime = System.currentTimeMillis();
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public long getAccessTime() {
    return accessTime;
  }

  public void updateAccessTime() {
    this.accessTime = System.currentTimeMillis();
  }

  @Override
  public String toString() {
    return "CacheEntry{" + "key=" + key + ", value=" + value + ", accessTime=" + accessTime + '}';
  }
}
