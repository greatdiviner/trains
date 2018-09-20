package com.thoughtworks.trains.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TownTestCase {

    @Test
    public void equals() {
        assertEquals(new Town("A"), new Town("A"));
    }

    @Test
    public void notEquals() {
        assertNotEquals(new Town("A"), new Town("B"));
    }
}
