package com.thoughtworks.trains.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.trains.model.Edge;
import com.thoughtworks.trains.model.GraphBuilder;
import com.thoughtworks.trains.model.Town;
import com.thoughtworks.trains.model.Trip;

public class CommandExecutorTestCase {

    private static CommandExecutor executor;
    private static ByteArrayOutputStream outputStream;

    @BeforeClass
    public static void beforeClass() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        executor = new CommandExecutor(ps, GraphBuilder.defaultGraph());
    }

    @Before
    public void before() {
        outputStream.reset();
    }

    @Test
    public void graph() {
        executor.graph(GraphBuilder.defaultGraph());
        assertEquals("", outputStream.toString().trim());
    }

    @Test
    public void distanceOfABC() {
        distance(Arrays.asList("A", "B", "C"), "9");
    }

    @Test
    public void distanceOfAD() {
        distance(Arrays.asList("A", "D"), "5");
    }

    @Test
    public void distanceOfADC() {
        distance(Arrays.asList("A", "D", "C"), "13");
    }

    @Test
    public void distanceOfAEBCD() {
        distance(Arrays.asList("A", "E", "B", "C", "D"), "22");
    }

    @Test
    public void distanceOfAED() {
        distance(Arrays.asList("A", "E", "D"), CommandExecutor.NO_SUCH_ROUTE);
    }

    private void distance(List<String> townNames, String expected) {
        Trip trip = new Trip(townNames.stream().map(Town::new).collect(Collectors.toList()));
        executor.distance(trip);
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void shortestOfAC() {
        shortest("A", "C", "9");
    }

    @Test
    public void shortestOfBB() {
        shortest("B", "B", "9");
    }

    private void shortest(String start, String end, String expected) {
        Edge edge = new Edge(new Town(start), new Town(end));
        executor.shortest(edge, "DISTANCE");
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void tripsOfCCwithMaxStopsOf3() {
        trips("MAX_STOPS", 3, "C", "C", "2");
    }

    @Test
    public void tripsOfACwithExactStopsOf3() {
        trips("EXACT_STOPS", 4, "A", "C", "3");
    }

    @Test
    public void tripsOfCCwithExactStopsOf3() {
        trips("MAX_DISTANCE", 30, "C", "C", "7");
    }

    private void trips(String criteria, int threshold, String start, String end, String expected) {
        Edge edge = new Edge(new Town(start), new Town(end));
        executor.trips(criteria, threshold, edge);
        assertEquals(expected, outputStream.toString().trim());
    }
}
