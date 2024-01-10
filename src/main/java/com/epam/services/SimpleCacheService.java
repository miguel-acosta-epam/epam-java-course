package com.epam.services;

import com.epam.models.CacheEntry;
import com.epam.utils.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleCacheService<K, V> {
  public static final int MAXIMUM_CACHE_SIZE = 100000;
  private final Map<K, CacheEntry<K, V>> cache = new LinkedHashMap<>(MAXIMUM_CACHE_SIZE);

  // AtomicLong helps with concurrency.
  private final AtomicLong cacheEvictions = new AtomicLong(0);
  private final AtomicLong totalPutTime = new AtomicLong(0);

  public V get(K key) {
    var entry = cache.get(key);

    if (entry == null) {
      return null;
    }

    entry.updateAccessTime();
    return entry.getValue();
  }

  public void put(K key, V value) {
    // To measure average time spent
    var startTime = System.nanoTime();

    if (cache.size() >= MAXIMUM_CACHE_SIZE) {
      evict();
    }

    var entry = new CacheEntry<K, V>(key, value);
    cache.put(key, entry);

    // To measure average time spent
    var endTime = System.nanoTime();
    totalPutTime.addAndGet(endTime - startTime);
  }

  public void evict() {
    CacheEntry<K, V> lastFrecuentEntry = null;
    for (var entry : cache.values()) {
      // Find the least frequently used entry
      if (lastFrecuentEntry == null || entry.getAccessTime() < lastFrecuentEntry.getAccessTime()) {
        lastFrecuentEntry = entry;
      }
    }

    if (lastFrecuentEntry != null) {
      cache.remove(lastFrecuentEntry.getKey());
      Logger.printLogMessage("Evicted: " + lastFrecuentEntry);
      cacheEvictions.incrementAndGet();
    }
  }

  public long getAveragePutTime() {
    return totalPutTime.get() / (cache.isEmpty() ? 1 : cache.size());
  }

  public long getCacheEvictions() {
    return cacheEvictions.get();
  }
}
