package ch.ebynaqon.aoc.aoc24.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

interface Day08 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        var antennasByFrequency = new HashMap<Character, List<Position>>();
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                char currentChar = line.charAt(col);
                if (currentChar != '.') {
                    List<Position> positions = antennasByFrequency.computeIfAbsent(currentChar, ArrayList::new);
                    positions.add(new Position(row, col));
                }
            }
        }
        return new ProblemInput(antennasByFrequency, rows, cols);
    }

    static long solvePart1(RawProblemInput input) {
        return countAntinodes(parseProblem(input), Day08::getAntinodes);
    }

    static long solvePart2(RawProblemInput input) {
        return countAntinodes(parseProblem(input), Day08::getAntinodesWithResonance);
    }

    private static int countAntinodes(ProblemInput problem, AntinodesFinder antinodesFinder) {
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> antennas : problem.antennasByFrequency().values()) {
            int numberOfAntennas = antennas.size();
            for (int i = 0; i < numberOfAntennas; i++) {
                Position first = antennas.get(i);
                for (int j = i + 1; j < numberOfAntennas; j++) {
                    Position second = antennas.get(j);
                    antinodes.addAll(antinodesFinder.find(first, second, problem::isWithinBounds));
                }
            }
        }
        return antinodes.size();
    }

    private static List<Position> getAntinodes(Position first, Position second, BoundsCheck boundsCheck) {
        List<Position> antinodePositions = new ArrayList<>();
        Delta delta = first.deltaTo(second);
        Position next = first.minus(delta);
        if (boundsCheck.isWithinBounds(next)) {
            antinodePositions.add(next);
        }
        next = second.plus(delta);
        if (boundsCheck.isWithinBounds(next)) {
            antinodePositions.add(next);
        }
        return antinodePositions;
    }

    private static List<Position> getAntinodesWithResonance(Position firstPosition, Position secondPosition, BoundsCheck boundsCheck) {
        List<Position> antinodePositions = new ArrayList<>();
        Delta delta = firstPosition.deltaTo(secondPosition);
        antinodePositions.add(firstPosition);
        Position next = secondPosition;
        while (boundsCheck.isWithinBounds(next)) {
            antinodePositions.add(next);
            next = next.plus(delta);
        }
        next = firstPosition.minus(delta);
        while (boundsCheck.isWithinBounds(next)) {
            antinodePositions.add(next);
            next = next.minus(delta);
        }
        return antinodePositions;
    }
}

@FunctionalInterface
interface AntinodesFinder {
    List<Position> find(Position firstPosition, Position secondPosition, BoundsCheck boundsCheck);
}

@FunctionalInterface
interface BoundsCheck {
    boolean isWithinBounds(Position position);
}

