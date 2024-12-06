package ch.ebynaqon.aoc.aoc24.day06;

record Position(int row, int column) {
    public Position next(Direction direction) {
        return new Position(row + direction.getDeltaRow(), column + direction.getDeltaCol());
    }
}

