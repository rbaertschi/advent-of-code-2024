package ch.ebynaqon.aoc.aoc24.day16;

record ProblemInput(boolean[][] obstacles, int[][][] costs, Position start, Position end) {
    public static final int TURN_COST = 1000;

    public int getCost(Position position, Direction direction) {
        return costs[direction.ordinal()][position.row()][position.col()];
    }

    public int setCost(Position position, Direction direction, int cost) {
        int minCost = Integer.MAX_VALUE;
        for (Direction dir : Direction.values()) {
            int oldCost = costs[dir.ordinal()][position.row()][position.col()];
            int newCost = cost + direction.turnsTo(dir) * TURN_COST;
            if (newCost < oldCost) {
                costs[dir.ordinal()][position.row()][position.col()] = newCost;
                minCost = Math.min(minCost, newCost);
            }
        }
        return minCost;
    }

    public boolean hasObstacleAt(Position position) {
        return obstacles[position.row()][position.col()];
    }
}

