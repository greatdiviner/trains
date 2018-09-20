package com.thoughtworks.trains.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EdgeTestCase {

    @Test
    public void equals() {
        Edge edge1 = new Edge(new Town("A"), new Town("B"));
        Edge edge2 = new Edge(new Town("A"), new Town("B"));
        assertEquals(edge1, edge2);
    }

    @Test
    public void notEquals() {
        Edge edge1 = new Edge(new Town("A"), new Town("B"));
        Edge edge2 = new Edge(new Town("A"), new Town("C"));
        assertNotEquals(edge1, edge2);
    }
}
