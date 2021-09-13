package com.newrelic.codingchallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
        System.out.println("he");
    }
}
