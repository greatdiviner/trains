package com.thoughtworks.trains.file;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Utility class to read command lines from input file.
 */
public final class FileUtil {

    private FileUtil() {
    }

    public static Optional<List<String>> readAllLinesFromFile(String filePath, boolean fromResource) {

        try {
            Path path;
            if (fromResource) {
                URL resource = FileUtil.class.getResource(filePath);
                path = Paths.get(resource.toURI());
            } else {
                path = Paths.get(filePath);
            }
            return Optional.ofNullable(Files.readAllLines(path));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
