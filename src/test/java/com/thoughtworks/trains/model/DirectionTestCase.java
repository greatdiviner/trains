package com.thoughtworks.trains.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectionTestCase {

    @Test
    public void compareToShorterDistance() {
        Direction dir1 = new Direction(new Town("A"), 3);
        Direction dir2 = new Direction(new Town("B"), 1);
        assertTrue(dir1.compareTo(dir2) > 0);
    }

    @Test
    public void compareToLongerDistance() {
        Direction dir1 = new Direction(new Town("A"), 1);
        Direction dir2 = new Direction(new Town("B"), 3);
        assertTrue(dir1.compareTo(dir2) < 0);
    }

    @Test
    public void compareToSameDistance() {
        Direction dir1 = new Direction(new Town("A"), 1);
        Direction dir2 = new Direction(new Town("B"), 1);
        assertTrue(dir1.compareTo(dir2) == 0);
    }
}
