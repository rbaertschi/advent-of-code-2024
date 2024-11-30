package ch.ebynaqon.aoc.helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class RawProblemInput {
    private final String input;

    public RawProblemInput(String input) {
        this.input = input.trim();
    }

    public String getWholeInput() {
        return input;
    }

    public List<String> getLines() {
        return Arrays.asList(input.split("\n"));
    }

    public static RawProblemInput fromResource(String resourcePath) {
        try {
            return new RawProblemInput(Files.readString(Path.of(RawProblemInput.class.getResource(resourcePath).toURI())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
