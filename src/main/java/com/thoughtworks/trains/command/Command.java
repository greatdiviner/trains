package com.thoughtworks.trains.command;

/**
 * Command interface.
 * 
 * @see GraphCommand
 * @see DistanceCommand
 * @see ShortestCommand
 * @see TripsCommand
 */
public interface Command {
    void execute(CommandExecutor executor);
}
