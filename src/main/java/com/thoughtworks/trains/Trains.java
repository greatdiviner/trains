package com.thoughtworks.trains;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.thoughtworks.trains.command.CommandExecutor;
import com.thoughtworks.trains.command.CommandFactory;
import com.thoughtworks.trains.file.FileUtil;
import com.thoughtworks.trains.model.Graph;

/**
 * Entry point class.
 */
public final class Trains {

    private static final String defaultFilePath = "/input.txt";

    private Trains() {
    }

    public static void main(String[] args) throws Exception {

        boolean fromResource = args.length == 0;
        String inputFilePath = fromResource ? defaultFilePath : args[0];

        Optional<List<String>> commandLines = FileUtil.readAllLinesFromFile(inputFilePath, fromResource);

        CommandExecutor executor = new CommandExecutor(System.out, new Graph(null));

        commandLines
        .ifPresent(lines -> lines
                .stream()
                .map(CommandFactory::createCommand)
                .filter(Objects::nonNull)
                .forEach(command -> command.execute(executor)));
    }

}
