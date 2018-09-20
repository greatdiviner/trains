package com.thoughtworks.trains.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class FileUtilTestCase {

    @Test
    public void readAllLinesFromFile() {
        List<String> lines = FileUtil.readAllLinesFromFile("/input-test.txt", true).get();
        assertFalse(lines.isEmpty());
        assertEquals(11, lines.size());
    }

    @Test
    public void readAllLinesFromFileWithWrongName() {
        Optional<List<String>> lines = FileUtil.readAllLinesFromFile("/non-existing-file-name.txt", true);
        assertFalse(lines.isPresent());
    }
}
