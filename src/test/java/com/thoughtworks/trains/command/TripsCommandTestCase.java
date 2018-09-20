package com.thoughtworks.trains.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.trains.model.GraphBuilder;

public class TripsCommandTestCase {

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
    public void executeCCwithMaxStopsOf3() {
        TripsCommand.fromCommandLine("Trips: MAX_STOPS,3,C-C").execute(executor);
        assertEquals("2", outputStream.toString().trim());
    }

    @Test
    public void executeACwithExactStopsOf3() {
        TripsCommand.fromCommandLine("Trips: EXACT_STOPS,4,A-C").execute(executor);
        assertEquals("3", outputStream.toString().trim());
    }

    @Test
    public void executeCCwithExactStopsOf3() {
        TripsCommand.fromCommandLine("Trips: MAX_DISTANCE,30,C-C").execute(executor);
        assertEquals("7", outputStream.toString().trim());
    }
}
