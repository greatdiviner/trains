package com.thoughtworks.trains.command;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.thoughtworks.trains.model.Direction;
import com.thoughtworks.trains.model.Edge;
import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.model.Town;
import com.thoughtworks.trains.model.Trip;

/**
 * A facade class for the trains problems.
 */
public class CommandExecutor {

    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    private PrintStream printStream;
    private Graph graph;

    public CommandExecutor(PrintStream printStream, Graph graph) {
        super();
        this.printStream = printStream;
        this.graph = graph;
    }

    public void graph(Graph graph) {
        this.graph = graph;
    }

    public void distance(Trip trip) {
        Integer distance = trip.calculateDistance(graph);
        printStream.println(distance == null ? NO_SUCH_ROUTE : distance);
    }

    public void shortest(Edge edge, String creteria) {
        Town start = edge.getStartTown();
        Town end = edge.getEndTown();

        PriorityQueue<Direction> priorityDirections = initializeMinHeap(start);
        int stopDuration = 0;
        if(creteria.equals("DISTANCE")) {
            stopDuration = 0;
        } else if (creteria.equals("DURATION")) {
            stopDuration = 2;
        }
        Integer distance = searchOnMinHeap(priorityDirections, end, stopDuration);

        printStream.println(distance == null ? NO_SUCH_ROUTE : distance);
    }

    private PriorityQueue<Direction> initializeMinHeap(Town start) {
        Set<Edge> edges = graph.getEdgeView().keySet();
        Set<Town> allTowns = new HashSet<>();
        edges.forEach(e -> {
            allTowns.add(e.getStartTown());
            allTowns.add(e.getEndTown());
        });

        Map<Town, Integer> distances = new HashMap<Town, Integer>();
        allTowns.forEach(t -> {
            distances.put(t, Integer.MAX_VALUE);
        });

        Set<Direction> directions = graph.getTownView().get(start);
        directions.forEach(d -> {
            distances.put(d.getTown(), d.getDistance());
        });

        Set<Direction> allDirections = distances.entrySet().stream().map(d -> {
            return new Direction(d.getKey(), d.getValue());
        }).collect(Collectors.toSet());

        return new PriorityQueue<Direction>(allDirections);
    }

    private Integer searchOnMinHeap(PriorityQueue<Direction> priorityDirections, Town end, Integer stopDuration) {
        Integer distance = null;
        Map<Edge, Integer> edgeMap = graph.getEdgeView();
        while (!priorityDirections.isEmpty()) {
            Direction shortestDirection = priorityDirections.remove();
            Town aTown = shortestDirection.getTown();

            if (aTown.equals(end)) {
                distance = shortestDirection.getDistance();
                break;
            }

            Integer aDistance = shortestDirection.getDistance();
            if (aDistance.equals(Integer.MAX_VALUE)) {
                break;
            }

            Set<Direction> updatedDirections = new HashSet<>();
            for (Direction dir : priorityDirections) {
                Town bTown = dir.getTown();
                Edge abEdge = new Edge(aTown, bTown);
                Integer abDistance = edgeMap.get(abEdge);
                if (abDistance == null) {
                    continue;
                }
                if (dir.getDistance() > aDistance + abDistance + stopDuration) {
                    dir.setDistance(aDistance + abDistance + stopDuration);
                    updatedDirections.add(dir);
                }
            }

            for (Direction dir : updatedDirections) {
                priorityDirections.remove(dir);
                priorityDirections.add(dir);
            }
        }
        return distance;
    }

    public void trips(String criteria, int threshold, Edge edge) {
        Predicate<Trip> stopCondition = t -> false;
        Predicate<Trip> returnCondition = t -> true;
        if (criteria.equalsIgnoreCase("MAX_STOPS")) {
            stopCondition = t -> t.getTowns().size() - 1 > threshold;
            returnCondition = t -> t.getTowns().size() - 1 <= threshold;
        } else if (criteria.equalsIgnoreCase("EXACT_STOPS")) {
            stopCondition = t -> t.getTowns().size() - 1 > threshold;
            returnCondition = t -> t.getTowns().size() - 1 == threshold;
        } else if (criteria.equalsIgnoreCase("MAX_DISTANCE")) {
            stopCondition = t -> t.calculateDistance(graph) >= threshold;
            returnCondition = t -> t.calculateDistance(graph) < threshold;
        } else if (criteria.equalsIgnoreCase("MAX_DURATION")) {
            stopCondition = t -> t.calculateDuration(graph) > threshold;
            returnCondition = t -> t.calculateDuration(graph) <= threshold;
        } else if (criteria.equalsIgnoreCase("EXACT_DURATION")) {
            stopCondition = t -> t.calculateDuration(graph) > threshold;
            returnCondition = t -> t.calculateDuration(graph) == threshold;
        }

        Town start = edge.getStartTown();
        Town end = edge.getEndTown();
        Map<Town, Set<Direction>> townView = graph.getTownView();

        Trip trip = new Trip(new ArrayList<>());
        trip.getTowns().add(start);

        Set<Direction> directions = townView.get(start);

        List<Trip> trips = search(trip, directions, stopCondition, returnCondition, end, townView);

        printStream.println(trips.size());
    }

    private List<Trip> search(Trip trip, Set<Direction> directions, Predicate<Trip> stopCondition,
            Predicate<Trip> returnCondition, Town end, Map<Town, Set<Direction>> townMap) {

        List<Trip> trips = new ArrayList<>();
        for (Direction dir : directions) {
            Trip thisTrip = new Trip(trip);
            Town last = dir.getTown();
            thisTrip.getTowns().add(last);

            if (stopCondition.test(thisTrip)) {
                continue;
            }

            if (last.equals(end) && returnCondition.test(thisTrip)) {
                trips.add(thisTrip);
            }

            trips.addAll(search(thisTrip, townMap.get(last), stopCondition, returnCondition, end, townMap));
        }

        return trips;
    }

    public void duration(Trip trip) {
        Integer duration = trip.calculateDuration(graph);
        printStream.println(duration == null ? NO_SUCH_ROUTE : duration);
    }
}
