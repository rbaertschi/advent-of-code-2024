package ch.ebynaqon.aoc.aoc24.day06;

class VisitedPositions {
    private final int rows;
    private final int cols;
    private final boolean[][][] directionVisited;
    private final boolean[][] visited;
    private int visitCount;

    public VisitedPositions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        directionVisited = new boolean[Direction.values().length][rows][cols];
        visited = new boolean[rows][cols];
        visitCount = 0;
    }

    public void addWaypoint(Position position, Direction direction) {
        directionVisited[direction.ordinal()][position.row()][position.column()] = true;
    }

    public void add(Position position) {
        if (!visited[position.row()][position.column()]) {
            visited[position.row()][position.column()] = true;
            visitCount++;
        }
    }

    public boolean alreadyWalked(Position position, Direction direction) {
        return directionVisited[direction.ordinal()][position.row()][position.column()];
    }

    public int getLength() {
        return visitCount;
    }

    public void reset() {
        for (int direction = 0; direction < Direction.values().length; direction++) {
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < cols; column++) {
                    directionVisited[direction][row][column] = false;
                    if (direction == 0) {
                        visited[row][column] = false;
                    }
                }
            }
        }
    }
}
