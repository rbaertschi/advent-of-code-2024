package ch.ebynaqon.aoc.aoc24.day16;

record ProblemInput(boolean[][] obstacles, int[][][] costs, int rows, int cols, Position start, Position end) {
    public static final int TURN_COST = 1000;

    ProblemInput(boolean[][] obstacles, int[][][] costs, Position start, Position end) {
        this(obstacles, costs, obstacles.length, obstacles[0].length, start, end);
    }

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

    public ProblemInput reverse() {
        boolean[][] newObstacles = new boolean[rows][cols];
        int[][][] newCosts = new int[Direction.values().length][rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                newObstacles[row][col] = this.obstacles[row][col];
                for (Direction dir : Direction.values()) {
                    newCosts[dir.ordinal()][row][col] = Integer.MAX_VALUE;
                }
            }
        }
        return new ProblemInput(newObstacles, newCosts, this.end, this.start);
    }
}

