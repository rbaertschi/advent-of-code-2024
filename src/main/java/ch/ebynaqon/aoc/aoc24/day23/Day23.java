package ch.ebynaqon.aoc.aoc24.day23;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<String> namesStartingWithT = problem.allNames().stream().filter(name -> name.startsWith("t")).collect(Collectors.toSet());
        Map<String, List<String>> connectionsFrom = problem.connectionMap(connections);
        Set<LanParty> result = new HashSet<>();
        for (String first : namesStartingWithT) {
            List<String> connectedToFirst = connectionsFrom.get(first);
            for (String second : connectedToFirst) {
                for (String third : connectionsFrom.get(second)) {
                    if (!third.equals(first) && connectedToFirst.contains(third)) {
                        result.add(LanParty.from(List.of(first, second, third)));
                    }
                }
            }
        }
        return result.size();
    }

    static String solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Connection> connections = problem.connections();
        Map<String, List<String>> connectionMap = problem.connectionMap(connections);
        Map<String, Set<LanParty>> partiesByName = new HashMap<>();
        for (String currentName : problem.allNames()) {
            HashSet<LanParty> singleParty = new HashSet<>();
            singleParty.add(LanParty.from(List.of(currentName)));
            partiesByName.put(currentName, singleParty);
        }
        for (String current : problem.allNames()) {
            List<String> connected = connectionMap.get(current);
            Set<LanParty> currentParties = partiesByName.get(current);
            for (String other : connected) {
                Set<LanParty> otherParties = partiesByName.get(other);
                for (LanParty otherParty : new HashSet<>(otherParties)) {
                    LanParty newParty = tryJoin(current, otherParty, connected);
                    if (newParty != null) {
                        otherParties.add(newParty);
                        currentParties.add(newParty);
                    }
                }
            }
        }
        return partiesByName.values().stream()
                .flatMap(Set::stream)
                .max(Comparator.comparing(LanParty::size))
                .orElseThrow()
                .password();
    }

    static LanParty tryJoin(String newMember, LanParty party, List<String> connectionsOfNewMember) {
        if (new HashSet<>(connectionsOfNewMember).containsAll(party.computerNames())) {
            return party.add(newMember);
        }
        return null;
    }

}

record LanParty(List<String> computerNames) {
    static LanParty from(Collection<String> computerNames) {
        List<String> sortedComputerNames = computerNames.stream().sorted().toList();
        return new LanParty(sortedComputerNames);
    }

    public int size() {
        return computerNames.size();
    }

    public String password() {
        return String.join(",", computerNames);
    }

    public LanParty add(String newMember) {
        ArrayList<String> names = new ArrayList<>(computerNames);
        names.add(newMember);
        return from(names);
    }
}

record ProblemInput(List<Connection> connections) {
    Set<String> allNames() {
        Set<String> names = new HashSet<>();
        for (Connection connection : connections) {
            names.add(connection.from());
            names.add(connection.to());
        }
        return names;
    }

    Map<String, List<String>> connectionMap(List<Connection> ignoredConnections) {
        Map<String, List<String>> connectionsFrom = new HashMap<>();
        connections().forEach(connection -> {
            connectionsFrom.computeIfAbsent(connection.from(), _ -> new ArrayList<>()).add(connection.to());
            connectionsFrom.computeIfAbsent(connection.to(), _ -> new ArrayList<>()).add(connection.from());
        });
        return connectionsFrom;
    }
}

record Connection(String from, String to) {
}

