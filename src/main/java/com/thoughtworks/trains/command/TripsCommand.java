package com.thoughtworks.trains.command;

import com.thoughtworks.trains.model.Edge;
import com.thoughtworks.trains.model.Town;

/**
 * A concrete {@link Command} class to solve trips problem.
 */
public final class TripsCommand implements Command {

    private String criteria;
    private int threshold;
    private Edge edge;

    private TripsCommand(String criteria, int threshold, Edge edge) {
        super();
        this.criteria = criteria;
        this.threshold = threshold;
        this.edge = edge;
    }

    public static Command fromCommandLine(String commandLine) {
        commandLine = commandLine.trim().toUpperCase();
        String parameters = commandLine.substring("TRIPS:".length()).trim();
        String[] commandParts = parameters.split(",");

        String theCriteria = commandParts[0].trim();
        int theThreshold = Integer.valueOf(commandParts[1].trim());
        String startTown = String.valueOf(commandParts[2].trim().charAt(0));
        String endTown = String.valueOf(commandParts[2].trim().charAt(2));

        return new TripsCommand(theCriteria, theThreshold, new Edge(new Town(startTown), new Town(endTown)));
    }

    @Override
    public void execute(CommandExecutor executor) {
        executor.trips(criteria, threshold, edge);
    }
}
