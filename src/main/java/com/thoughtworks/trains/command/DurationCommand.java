package com.thoughtworks.trains.command;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.trains.model.Town;
import com.thoughtworks.trains.model.Trip;

/**
 * A concrete {@link Command} class to solve duration problem.
 */
public final class DurationCommand implements Command {

    private Trip trip;

    private DurationCommand(Trip trip) {
        super();
        this.trip = trip;
    }

    public static Command fromCommandLine(String commandLine) {
        commandLine = commandLine.trim().toUpperCase();
        String parameters = commandLine.substring("DURATION:".length()).trim();
        String[] towns = parameters.split("-");

        List<Town> townList = new ArrayList<>();
        for (String t : towns) {
            townList.add(new Town(t));
        }

        Trip trip = new Trip(townList);

        return new DurationCommand(trip);
    }

    @Override
    public void execute(CommandExecutor executor) {
        executor.duration(trip);
    }
}
