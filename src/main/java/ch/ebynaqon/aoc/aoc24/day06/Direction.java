package ch.ebynaqon.aoc.aoc24.day06;

enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    private final int deltaRow;
    private final int deltaCol;

    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public static Direction fromTo(Position currentPosition, Position nextPosition) {
        if (currentPosition.row() == nextPosition.row()) {
            return currentPosition.column() > nextPosition.column() ? LEFT : RIGHT;
        } else {
            return currentPosition.row() > nextPosition.row() ? UP : DOWN;
        }
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaCol() {
        return deltaCol;
    }

    public Direction turn() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case RIGHT -> DOWN;
        };
    }
}
