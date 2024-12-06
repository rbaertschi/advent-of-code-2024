package ch.ebynaqon.aoc.aoc24.day06;

import java.util.ArrayList;
import java.util.List;

record Guard(Position position, Direction direction) {
    public List<Position> walkToObstacle(Position obstacle) {
        Position currentPosition = position;
        ArrayList<Position> positions = new ArrayList<>();
        while (!currentPosition.equals(obstacle)) {
            positions.add(currentPosition);
            currentPosition = currentPosition.next(direction);
        }
        return positions;
    }
}
