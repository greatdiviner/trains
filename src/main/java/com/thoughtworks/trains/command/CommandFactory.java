package com.thoughtworks.trains.command;

/**
 * Simple factory class to create an appropriate {@link Command} instance from a command line.
 */
public final class CommandFactory {

    private static final String GRAPH_REGEX = "GRAPH:\\s*(\\D\\D\\d+)(\\s*,\\s*\\D\\D\\d+)*";
    private static final String DISTANCE_REGEX = "DISTANCE:\\s*\\D-\\D(-\\D)*";
    private static final String DURATION_REGEX = "DURATION:\\s*\\D-\\D(-\\D)*";
    private static final String SHORTEST_REGEX = "SHORTEST:\\s*(DISTANCE|DURATION)\\s*,\\s*\\D-\\D";
    private static final String TRIPS_REGEX = "TRIPS:\\s*(MAX_STOPS|EXACT_STOPS|MAX_DISTANCE|MAX_DURATION|EXACT_DURATION)\\s*,\\s*\\d+\\s*,\\s*\\D-\\D";

    private CommandFactory() {
    }

    public static Command createCommand(String commandLine) {
        commandLine = commandLine.trim().toUpperCase();
        Command command = null;
        if (commandLine.matches(GRAPH_REGEX)) {
            command = GraphCommand.fromCommandLine(commandLine);
        } else if (commandLine.matches(DISTANCE_REGEX)) {
            command = DistanceCommand.fromCommandLine(commandLine);
        } else if (commandLine.matches(SHORTEST_REGEX)) {
            command = ShortestCommand.fromCommandLine(commandLine);
        } else if (commandLine.matches(TRIPS_REGEX)) {
            command = TripsCommand.fromCommandLine(commandLine);
        } else if (commandLine.matches(DURATION_REGEX)) {
            command = DurationCommand.fromCommandLine(commandLine);
        }

        return command;
    }
}
