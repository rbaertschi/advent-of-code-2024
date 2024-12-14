package ch.ebynaqon.aoc.aoc24.day14;

record Robot(Position position, Velocity velocity) {
}

record Position(int x, int y) {
    public Position moveWithTeleportation(Velocity velocity, int width, int height) {
        return new Position(
                (x + velocity.dx() + width) % width,
                (y + velocity.dy() + height) % height
        );
    }
}

record Velocity(int dx, int dy) {}
