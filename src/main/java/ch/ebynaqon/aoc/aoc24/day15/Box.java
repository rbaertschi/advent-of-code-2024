package ch.ebynaqon.aoc.aoc24.day15;

import java.util.List;

record Box(int index, List<Position> positions) {
    public Box move(Movement movement) {
        return new Box(index, positions.stream().map(position -> position.move(movement)).toList());
    }

    public List<Position> pushingPositions(Movement movement) {
        return positions.stream().map(position -> position.move(movement))
                .filter(position -> !positions.contains(position))
                .toList();
    }
}
