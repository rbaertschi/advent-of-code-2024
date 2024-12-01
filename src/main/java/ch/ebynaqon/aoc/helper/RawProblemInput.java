package ch.ebynaqon.aoc.helper;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RawProblemInput {
    private final String input;

    public RawProblemInput(String input) {
        this.input = input.trim();
    }

    @SuppressWarnings("unused")
    public String getWholeInput() {
        return input;
    }

    public List<String> getLines() {
        return Arrays.asList(input.split("\n"));
    }

    public static RawProblemInput fromResource(String resourcePath) {
        try {
            URI resourceURI = Objects.requireNonNull(RawProblemInput.class.getResource(resourcePath)).toURI();
            return new RawProblemInput(Files.readString(Path.of(resourceURI)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
