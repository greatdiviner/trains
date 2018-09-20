package com.thoughtworks.trains.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class GraphTestCase {

    private static Graph graph = GraphBuilder.defaultGraph();

    @Test
    public void getEdgeView() {
        Map<Edge, Integer> edgeView = graph.getEdgeView();

        Edge ab = new Edge(new Town("A"), new Town("B"));
        assertEquals(Integer.valueOf(5), edgeView.get(ab));

        Edge ac = new Edge(new Town("A"), new Town("C"));
        assertNull(edgeView.get(ac));

        Edge hi = new Edge(new Town("H"), new Town("I"));
        assertNull(edgeView.get(hi));
    }

    @Test
    public void getTownView() {
        Map<Town, Set<Direction>> townView = graph.getTownView();

        Town a = new Town("A");
        assertEquals(3, townView.get(a).size());

        Town h = new Town("H");
        assertNull(townView.get(h));
    }
}
