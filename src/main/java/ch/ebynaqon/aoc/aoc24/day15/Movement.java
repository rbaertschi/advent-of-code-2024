package ch.ebynaqon.aoc.aoc24.day15;

enum Movement {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Movement(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }

    public Movement reverse() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public static Movement from(String input) {
        return switch (input) {
            case "^" -> UP;
            case "v" -> DOWN;
            case "<" -> LEFT;
            case ">" -> RIGHT;
            default -> throw new IllegalArgumentException();
        };
    }
}
