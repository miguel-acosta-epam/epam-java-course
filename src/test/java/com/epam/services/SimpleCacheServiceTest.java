package com.epam.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleCacheServiceTest {
  @Test
  public void testSimpleCacheService() {
    var cacheService = new SimpleCacheService<String, String>();

    cacheService.put("key1", "value1");
    assertEquals("value1", cacheService.get("key1"));

    for (int i = 0; i < SimpleCacheService.MAXIMUM_CACHE_SIZE; i++) {
      cacheService.put("iteratedKey" + i, "value" + i);
    }
    assertNull(cacheService.get("key1"), "key1 should be evicted");

    assertEquals(1, cacheService.getCacheEvictions(), "Should have 1 eviction registered");
    assertTrue(
        cacheService.getAveragePutTime() >= 0, "Should have an average put time greater than 0");
  }
}
