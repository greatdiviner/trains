package com.thoughtworks.trains.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Model class representing a graph.
 */
public class Graph {

    private Map<Edge, Integer> edgeView = new HashMap<>();
    private Map<Town, Set<Direction>> townView = new HashMap<>();

    public Graph(Map<Edge, Integer> edgeView) {
        super();
        if (edgeView == null) {
            return;
        }

        this.edgeView = edgeView;

        edgeView.entrySet().forEach(entry -> {
            Edge edge = entry.getKey();
            Integer distance = entry.getValue();

            Town town = edge.getStartTown();
            Direction direction = new Direction(edge.getEndTown(), distance);

            if (!townView.containsKey(town)) {
                townView.put(town, new HashSet<>());
            }
            townView.get(town).add(direction);

        });
    }

    public Map<Edge, Integer> getEdgeView() {
        return edgeView;
    }

    public Map<Town, Set<Direction>> getTownView() {
        return townView;
    }

}
