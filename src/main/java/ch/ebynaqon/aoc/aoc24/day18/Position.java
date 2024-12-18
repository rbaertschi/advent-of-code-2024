package ch.ebynaqon.aoc.aoc24.day18;

import java.util.List;

record Position(int x, int y) {
    public List<Position> neighbours() {
        return List.of(
                new Position(x + 1, y),
                new Position(x - 1, y),
                new Position(x, y + 1),
                new Position(x, y - 1)
        );
    }
}

