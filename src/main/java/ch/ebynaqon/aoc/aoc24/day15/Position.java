package ch.ebynaqon.aoc.aoc24.day15;

record Position(int x, int y) {
    public Position move(Movement movement) {
        return new Position(x + movement.dx(), y + movement.dy());
    }
}
