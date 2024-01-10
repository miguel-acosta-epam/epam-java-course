package com.epam.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuavaCacheServiceTest {
  @Test
  public void testGuavaCacheService() {
    var guavaCacheService = new GuavaCacheService<String, String>();

    guavaCacheService.put("key1", "value1");
    assertEquals("value1", guavaCacheService.get("key1"));

    for (int i = 0; i < GuavaCacheService.MAXIMUM_CACHE_SIZE; i++) {
      guavaCacheService.put("iteratedKey" + i, "value" + i);
    }
    assertNull(guavaCacheService.get("key1"), "key1 should be evicted");

    assertEquals(1, guavaCacheService.getCacheEvictions(), "Should have 1 eviction registered");
    assertTrue(
        guavaCacheService.getAveragePutTime() >= 0,
        "Should have an average put time greater than 0");
  }
}
