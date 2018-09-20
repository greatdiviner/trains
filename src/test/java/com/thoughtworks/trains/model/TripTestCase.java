package com.thoughtworks.trains.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TripTestCase {

    private static Graph graph = GraphBuilder.defaultGraph();

    @Test
    public void calculateDistanceOfABC() {
        verifyDistance(Arrays.asList("A", "B", "C"), 9);
    }

    @Test
    public void calculateDistanceOfAD() {
        verifyDistance(Arrays.asList("A", "D"), 5);
    }

    @Test
    public void calculateDistanceOfADC() {
        verifyDistance(Arrays.asList("A", "D", "C"), 13);
    }

    @Test
    public void calculateDistanceOfAEBCD() {
        verifyDistance(Arrays.asList("A", "E", "B", "C", "D"), 22);
    }

    @Test
    public void calculateDistanceOfAED() {
        verifyDistance(Arrays.asList("A", "E", "D"), null);
    }

    private void verifyDistance(List<String> townNames, Integer expected) {
        Trip trip = new Trip(townNames.stream().map(Town::new).collect(Collectors.toList()));
        Integer actual = trip.calculateDistance(graph);
        assertEquals(expected, actual);
    }
}
