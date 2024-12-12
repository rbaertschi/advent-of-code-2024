package ch.ebynaqon.aoc.aoc24.day12;

import java.util.List;

public record Position(int row, int column) {
    public List<Position> neighbours() {
        return List.of(
                new Position(row - 1, column),
                new Position(row + 1, column),
                new Position(row, column - 1),
                new Position(row, column + 1)
        );
    }
}
