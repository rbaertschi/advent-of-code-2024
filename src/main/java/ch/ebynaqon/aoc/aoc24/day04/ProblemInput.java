package ch.ebynaqon.aoc.aoc24.day04;

import java.util.List;

record ProblemInput(List<String> lines, int rows, int columns) {
    char getCharAt(int row, int col) {
        if (row < 0 || row >= lines.size() || col < 0 || col >= columns) {
            return ' ';
        }
        return lines.get(row).charAt(col);
    }

    public void visitEachPosition(Visitor visitor) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                visitor.visit(row, col);
            }
        }
    }
}

interface Visitor {
    void visit(int row, int col);
}
