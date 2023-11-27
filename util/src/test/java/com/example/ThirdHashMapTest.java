package com.example;

import com.example.util.ThirdHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThirdHashMapTest {

    private ThirdHashMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new ThirdHashMap<>();
    }

    @Test
    public void test1() {
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        Assertions.assertEquals("1", map.get("1"));
        Assertions.assertEquals("2", map.get("2"));
        Assertions.assertEquals("3", map.get("3"));
    }
}
