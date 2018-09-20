package com.thoughtworks.trains.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.trains.model.GraphBuilder;

public class ShortestCommandTestCase {
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
    public void executeAC() {
        ShortestCommand.fromCommandLine("Shortest: DISTANCE, A-C").execute(executor);
        assertEquals("9", outputStream.toString().trim());
    }

    @Test
    public void executeBB() {
        ShortestCommand.fromCommandLine("Shortest: DISTANCE, B-B").execute(executor);
        assertEquals("9", outputStream.toString().trim());
    }
}
