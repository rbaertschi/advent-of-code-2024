package ch.ebynaqon.aoc.aoc24.day06;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

final class ProblemInput {
    private Guard guard;
    private final boolean[][] obstacles;
    private final int rows;
    private final int cols;
    private final VisitedPositions visitedPositions;

    ProblemInput(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.obstacles = new boolean[rows][cols];
        visitedPositions = new VisitedPositions(rows, cols);
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public void addObstacle(int row, int col) {
        obstacles[row][col] = true;
    }

    public Position walkToNextObstacle(Guard currentGuard, Consumer<Position> onVisitNewPosition, BiConsumer<Position, Direction> addWaypoint) {
        Direction direction = currentGuard.direction();
        Position position = currentGuard.position();
        int deltaRow = direction.getDeltaRow();
        int deltaCol = direction.getDeltaCol();
        addWaypoint.accept(position, direction);
        onVisitNewPosition.accept(position);
        int row = position.row() + deltaRow;
        int col = position.column() + deltaCol;
        while (row >= 0 && row < rows && col >= 0 && col < cols) {
            if (obstacles[row][col]) {
                return new Position(row - deltaRow, col - deltaCol);
            } else {
                onVisitNewPosition.accept(new Position(row, col));
            }
            row += deltaRow;
            col += deltaCol;
        }
        return null;
    }

    public Guard guard() {
        return guard;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public boolean[][] getObstacles() {
        return obstacles;
    }

    public boolean isObstacle(int row, int col) {
        return obstacles[row][col];
    }

    public void clearObstacle(int row, int col) {
        obstacles[row][col] = false;
    }

    public VisitedPositions visited() {
        return visitedPositions;
    }
}

