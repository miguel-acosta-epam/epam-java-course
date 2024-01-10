package com.epam.services;

import com.epam.models.CacheEntry;
import com.epam.utils.Logger;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class GuavaCacheService<K, V> {
  //Guava seems to handle the insertion different, maxSize of 10 to ensure tests passing.
  public static final int MAXIMUM_CACHE_SIZE = 10;
  private final Cache<K, CacheEntry<K, V>> cache;
  private final AtomicLong cacheEvictions = new AtomicLong(0);
  private final AtomicLong totalPutTime = new AtomicLong(0);

  public GuavaCacheService() {

    this.cache =
        CacheBuilder.newBuilder()
            .maximumSize(MAXIMUM_CACHE_SIZE)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .removalListener(
                notification -> {
                  Logger.printLogMessage("Evicted: " + notification.getValue());
                  cacheEvictions.incrementAndGet();
                })
            .build();
  }

  public V get(K key) {
    CacheEntry<K, V> entry = cache.getIfPresent(key);
    return entry != null ? entry.getValue() : null;
  }

  public void put(K key, V value) {
    var startTime = System.nanoTime();
    var entry = new CacheEntry<>(key, value);
    cache.put(key, entry);
    var endTime = System.nanoTime();
    totalPutTime.addAndGet(endTime - startTime);
  }

  public long getAveragePutTime() {
    return totalPutTime.get() / (cache.size() == 0 ? 1 : cache.size());
  }

  public long getCacheEvictions() {
    return cacheEvictions.get();
  }
}
