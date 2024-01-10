package com.epam;

import com.epam.services.GuavaCacheService;
import com.epam.services.SimpleCacheService;

public class App {
  public static void main(String[] args) {
    System.out.println("====== Simple Java cache implementation =======");
    var cacheService = new SimpleCacheService<String, String>();

    cacheService.put("key1", "value1");
    cacheService.put("key2", "value2");
    cacheService.put("key3", "value3");

    System.out.println("Get key2: " + cacheService.get("key2"));
    System.out.println("Get key3: " + cacheService.get("key3"));

    cacheService.put("key4", "value4");

    System.out.println("Average Put Time: " + cacheService.getAveragePutTime());
    System.out.println("Cache Evictions: " + cacheService.getCacheEvictions());

    // GUAVA usage:
    // Note: Guava does not provide direct access to average put time and cache evictions
    System.out.println("====== Guava cache implementation =======");

    var guavaCacheService = new GuavaCacheService<String, String>();

    guavaCacheService.put("key1", "value1");
    guavaCacheService.put("key2", "value2");
    guavaCacheService.put("key3", "value3");

    System.out.println("Get key2: " + guavaCacheService.get("key2"));
    System.out.println("Get key3: " + guavaCacheService.get("key3"));

    guavaCacheService.put("key4", "value4");

    System.out.println("Average Put Time: " + guavaCacheService.getAveragePutTime());
    System.out.println("Cache Evictions: " + guavaCacheService.getCacheEvictions());
  }
}
