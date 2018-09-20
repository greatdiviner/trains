package com.thoughtworks.trains.model;

import java.util.HashMap;
import java.util.Map;

public final class GraphBuilder {

    private GraphBuilder() {
    }

    public static Graph defaultGraph() {
        Map<Edge, Integer> edgeView = new HashMap<>();
        edgeView.put(new Edge(new Town("A"), new Town("B")), 5);
        edgeView.put(new Edge(new Town("B"), new Town("C")), 4);
        edgeView.put(new Edge(new Town("C"), new Town("D")), 8);
        edgeView.put(new Edge(new Town("D"), new Town("C")), 8);
        edgeView.put(new Edge(new Town("D"), new Town("E")), 6);
        edgeView.put(new Edge(new Town("A"), new Town("D")), 5);
        edgeView.put(new Edge(new Town("C"), new Town("E")), 2);
        edgeView.put(new Edge(new Town("E"), new Town("B")), 3);
        edgeView.put(new Edge(new Town("A"), new Town("E")), 7);
        return new Graph(edgeView);
    }
}
