package ch.ebynaqon.aoc.aoc24.day10;

record ProblemInput(int rows, int cols, int[][] heightMap) {
    public boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < rows && position.col() >= 0 && position.col() < cols;
    }

    public int getHeight(Position position) {
        return heightMap[position.row()][position.col()];
    }
}

