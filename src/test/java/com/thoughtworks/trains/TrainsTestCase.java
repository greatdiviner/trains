package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrainsTestCase {
    private static ByteArrayOutputStream outputStream;

    @BeforeClass
    public static void beforeClass() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Before
    public void before() {
        outputStream.reset();
    }

    @Test
    public void main() throws Exception {
        String[] args = {};
        Trains.main(args);
        assertNotEquals("", outputStream.toString().trim());
    }

    @Test
    public void main2() throws Exception {
        String[] args = { "/non-existing-file-name.txt" };
        Trains.main(args);
        assertEquals("", outputStream.toString().trim());
    }
}
