package com.thoughtworks.trains.command;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandFactoryTestCase {

    @Test
    public void createGraphCommand() {
        String commandLine = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        Command command = CommandFactory.createCommand(commandLine);

        assertNotNull(command);
        assertTrue(command instanceof GraphCommand);
    }

    @Test
    public void createDistanceCommand() {
        String commandLine = "Distance: A-B-C";
        Command command = CommandFactory.createCommand(commandLine);

        assertNotNull(command);
        assertTrue(command instanceof DistanceCommand);
    }

    @Test
    public void createTripsCommand() {
        String commandLine = "Trips: MAX_STOPS,3,C-C";
        Command command = CommandFactory.createCommand(commandLine);

        assertNotNull(command);
        assertTrue(command instanceof TripsCommand);
    }

    @Test
    public void createShortestCommand() {
        String commandLine = "Shortest: DISTANCE, A-C";
        Command command = CommandFactory.createCommand(commandLine);

        assertNotNull(command);
        assertTrue(command instanceof ShortestCommand);
    }
}
