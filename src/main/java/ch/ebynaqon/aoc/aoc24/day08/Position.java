package ch.ebynaqon.aoc.aoc24.day08;

record Position(int row, int column) {
    public Delta deltaTo(Position other) {
        return new Delta(other.row() - row, other.column() - column);
    }

    public Position plus(Delta delta) {
        return new Position(row + delta.row(), column + delta.col());
    }

    public Position minus(Delta delta) {
        return new Position(row - delta.row(), column - delta.col());
    }
}

