package com.thoughtworks.trains.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.trains.model.GraphBuilder;

public class DistanceCommandTestCase {
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
    public void executeABC() {
        DistanceCommand.fromCommandLine("Distance: A-B-C").execute(executor);
        assertEquals("9", outputStream.toString().trim());
    }

    @Test
    public void executeAD() {
        DistanceCommand.fromCommandLine("Distance: A-D").execute(executor);
        assertEquals("5", outputStream.toString().trim());
    }

    @Test
    public void executeADC() {
        DistanceCommand.fromCommandLine("Distance: A-D-C").execute(executor);
        assertEquals("13", outputStream.toString().trim());
    }

    @Test
    public void executeAEBCD() {
        DistanceCommand.fromCommandLine("Distance: A-E-B-C-D").execute(executor);
        assertEquals("22", outputStream.toString().trim());
    }

    @Test
    public void executeAED() {
        DistanceCommand.fromCommandLine("Distance: A-E-D").execute(executor);
        assertEquals(CommandExecutor.NO_SUCH_ROUTE, outputStream.toString().trim());
    }
}
