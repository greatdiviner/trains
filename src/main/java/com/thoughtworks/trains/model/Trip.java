package com.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Model class representing a trip.
 */
public class Trip {

    private List<Town> towns;

    public Trip(List<Town> towns) {
        super();
        this.towns = towns;
    }

    public Trip(Trip trip) {
        super();
        this.towns = new ArrayList<>(trip.getTowns());
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public Integer calculateDistance(Graph graph) {
        Map<Edge, Integer> edgeView = graph.getEdgeView();
        if (edgeView.isEmpty()) {
            return null;
        }

        Integer distance = 0;
        for (int i = 0; i < towns.size() - 1; i++) {
            Edge edge = new Edge(towns.get(i), towns.get(i + 1));

            if (!edgeView.containsKey(edge)) {
                return null;
            }
            distance += edgeView.get(edge);
        }
        return distance;
    }

    public Integer calculateDuration(Graph graph) {
        Integer distance = calculateDistance(graph);

        if (distance == null) {

            return null;
        }

        Integer stopDuration = (towns.size() - 2) * 2;

        return distance + stopDuration;
    }
}
