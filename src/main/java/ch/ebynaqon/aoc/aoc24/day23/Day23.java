package ch.ebynaqon.aoc.aoc24.day23;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

interface Day23 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Connection> samples = input.getLines().stream().map(Day23::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static Connection parseLine(String input) {
        String[] parts = input.split("-");
        return new Connection(parts[0], parts[1]);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Connection> connections = problem.connections();
        Map<String, List<String>> connectionsFrom = new HashMap<>();
        Set<String> namesStartingWithT = new HashSet<>();
        connections.forEach(connection -> {
            connectionsFrom.computeIfAbsent(connection.from(), _ -> new ArrayList<>()).add(connection.to());
            if (connection.from().startsWith("t")) {
                namesStartingWithT.add(connection.from());
            }
            connectionsFrom.computeIfAbsent(connection.to(), _ -> new ArrayList<>()).add(connection.from());
            if (connection.to().startsWith("t")) {
                namesStartingWithT.add(connection.to());
            }
        });
        Set<ComputerTriple> result = new HashSet<>();
        for (String first : namesStartingWithT) {
            List<String> connectedToFirst = connectionsFrom.get(first);
            for (String second : connectedToFirst) {
                for (String third : connectionsFrom.get(second)) {
                    if (!third.equals(first) && connectedToFirst.contains(third)) {
                        result.add(ComputerTriple.from(first, second, third));
                    }
                }
            }
        }
        return result.size();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

record ComputerTriple(String first, String second, String third) {
    static ComputerTriple from(String s1, String s2, String s3) {
        List<String> sorted = Stream.of(s1, s2, s3).sorted().toList();
        return new ComputerTriple(sorted.get(0), sorted.get(1), sorted.get(2));
    }
}

record ProblemInput(List<Connection> connections) {
}

record Connection(String from, String to) {
}

