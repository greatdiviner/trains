package com.thoughtworks.trains.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.trains.model.GraphBuilder;

public class GraphCommandTestCase {
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
    public void execute() {
        GraphCommand.fromCommandLine("Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7").execute(executor);
        assertEquals("", outputStream.toString().trim());
    }

}
