package ch.ebynaqon.aoc.aoc24.day08;

import java.util.List;
import java.util.Map;

record ProblemInput(Map<Character, List<Position>> antennasByFrequency, int rows, int columns) {
    public boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < rows && position.column() >= 0 && position.column() < columns;
    }
}

