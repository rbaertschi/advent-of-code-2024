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
        ProblemInput problem = parseProblem(input);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> antennas : problem.antennasByFrequency().values()) {
            int numberOfAntennas = antennas.size();
            for (int i = 0; i < numberOfAntennas; i++) {
                Position first = antennas.get(i);
                for (int j = i + 1; j < numberOfAntennas; j++) {
                    Position second = antennas.get(j);
                    Delta delta = first.deltaTo(second);
                    antinodes.add(first.minus(delta));
                    antinodes.add(second.plus(delta));
                }
            }
        }
        return antinodes.stream().filter(antinode -> isWithinBounds(antinode, problem.rows(), problem.columns())).count();
    }

    static boolean isWithinBounds(Position position, int rows, int columns) {
        return position.row() >= 0 && position.row() < rows && position.column() >= 0 && position.column() < columns;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

