package ch.ebynaqon.aoc.aoc24.day06;

import java.util.List;

record ProblemInput(Guard guard, List<Position> obstacles, int rows, int cols) {
    public Position getObstacleFacedBy(Guard currentGuard) {
        List<Position> potentialObstacles = obstacles.stream().filter(obstacle -> switch (currentGuard.direction()) {
            case UP, DOWN -> obstacle.column() == currentGuard.position().column();
            case LEFT, RIGHT -> obstacle.row() == currentGuard.position().row();
        }).toList();
        Position nextObstaclePosition = currentGuard.position();
        while (isWithinBounds(nextObstaclePosition)) {
            nextObstaclePosition = nextObstaclePosition.next(currentGuard.direction());
            if (potentialObstacles.contains(nextObstaclePosition)) {
                return nextObstaclePosition;
            }
        }
        return null;
    }

    private boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < rows() && position.column() >= 0 && position.column() < cols();
    }

    public Position getEdgeFacedBy(Guard guard) {
        return switch (guard.direction()) {
            case UP -> new Position(-1, guard.position().column());
            case DOWN -> new Position(rows, guard.position().column());
            case LEFT -> new Position(guard.position().row(), -1);
            case RIGHT -> new Position(guard.position().row(), cols);
        };
    }
}

