package com.thoughtworks.trains.command;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.trains.model.Edge;
import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.model.Town;

/**
 * A concrete {@link Command} class to solve graph problem.
 */
public final class GraphCommand implements Command {

    private Graph graph;

    private GraphCommand(Graph graph) {
        super();
        this.graph = graph;
    }

    public static Command fromCommandLine(String commandLine) {
        commandLine = commandLine.trim().toUpperCase();
        String parameters = commandLine.substring("GRAPH:".length()).trim();
        String[] routes = parameters.split(",");

        Map<Edge, Integer> edgeView = new HashMap<>();
        for (String route : routes) {
            String trimmed = route.trim();
            String start = String.valueOf(trimmed.charAt(0));
            String end = String.valueOf(trimmed.charAt(1));
            int distance = Integer.valueOf(trimmed.substring(2));

            edgeView.put(new Edge(new Town(start), new Town(end)), distance);
        }

        Graph graph = new Graph(edgeView);

        return new GraphCommand(graph);
    }

    @Override
    public void execute(CommandExecutor executor) {
        executor.graph(graph);
    }

}
