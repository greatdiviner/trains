package com.thoughtworks.trains.command;

import com.thoughtworks.trains.model.Edge;
import com.thoughtworks.trains.model.Town;

/**
 * A concrete {@link Command} class to solve shortest problem.
 */
public final class ShortestCommand implements Command {

    private Edge edge;
    private String creteria;

    private ShortestCommand(Edge edge, String creteria) {
        super();
        this.edge = edge;
        this.creteria = creteria;
    }

    public static Command fromCommandLine(String commandLine) {
        commandLine = commandLine.trim().toUpperCase();
        String parameters = commandLine.substring("SHORTEST:".length()).trim();
        String[] commandParts = parameters.split(",");

        String theCriteria = commandParts[0].trim();
        String[] towns = commandParts[1].trim().split("-");

        return new ShortestCommand(new Edge(new Town(towns[0]), new Town(towns[1])), theCriteria);
    }

    @Override
    public void execute(CommandExecutor executor) {
        executor.shortest(edge, creteria);
    }
}
