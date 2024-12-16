package ch.ebynaqon.aoc.aoc24.day16;

enum Direction {
    North(-1, 0), East(0, 1), South(1, 0), West(0, -1);

    private final int deltaRow;
    private final int deltaCol;

    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    int turnsTo(Direction other) {
        if (this.equals(other)) {
            return 0;
        } else if (this.ordinal() % 2 == other.ordinal() % 2) {
            return 2;
        } else {
            return 1;
        }
    }

    public int deltaRow() {
        return deltaRow;
    }

    public int deltaCol() {
        return deltaCol;
    }
}
