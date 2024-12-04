package ch.ebynaqon.aoc.aoc24.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

interface Day04 {

    Pattern XMAS = Pattern.compile("XMAS");

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> horizontalLines = input.getLines();
        int rows = horizontalLines.size();
        int cols = horizontalLines.getFirst().length();
        List<String> verticalLines = getVerticalLines(cols, rows, horizontalLines);
        List<String> diagonalDownLines = getDiagonalDownLines(cols, rows, horizontalLines);
        List<String> diagonalUpLines = getDiagonalUpLines(cols, rows, horizontalLines);
        return new ProblemInput(horizontalLines, verticalLines, diagonalDownLines, diagonalUpLines);
    }

    static List<String> getDiagonalDownLines(int cols, int rows, List<String> horizontalLines) {
        List<String> lines = new ArrayList<>(cols + rows - 1);
        for (int row = rows - 1; row >= 0; row--) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < cols && (row + col) < rows; col++) {
                int actualRow = row + col;
                line.append(horizontalLines.get(actualRow).charAt(col));
            }
            lines.add(line.toString());
        }
        for (int col = 1; col < cols; col++) {
            StringBuilder line = new StringBuilder();
            for (int row = 0; row < rows && (row + col) < cols; row++) {
                int actualCol = col + row;
                line.append(horizontalLines.get(row).charAt(actualCol));
            }
            lines.add(line.toString());
        }
        return lines;
    }

    static List<String> getDiagonalUpLines(int cols, int rows, List<String> horizontalLines) {
        List<String> lines = new ArrayList<>(cols + rows - 1);
        for (int col = cols - 1; col >= 0; col--) {
            StringBuilder line = new StringBuilder();
            for (int delta = 0; (col + delta) < cols && (rows - 1 - delta) >= 0; delta++) {
                int actualCol = col + delta;
                int actualRow = rows - 1 - delta;
                line.append(horizontalLines.get(actualRow).charAt(actualCol));
            }
            lines.add(line.toString());
        }
        for (int row = rows - 2; row >= 0; row--) {
            StringBuilder line = new StringBuilder();
            for (int column = 0; column < cols && (row - column) >= 0; column++) {
                int actualRow = row - column;
                line.append(horizontalLines.get(actualRow).charAt(column));
            }
            lines.add(line.toString());
        }
        return lines;
    }

    private static List<String> getVerticalLines(int cols, int rows, List<String> horizontalLines) {
        List<String> verticalLines = new ArrayList<>(cols);
        for (int col = 0; col < cols; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                column.append(horizontalLines.get(row).charAt(col));
            }
            verticalLines.add(column.toString());
        }
        return verticalLines;
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return countXmasInLines(problem.horizontalLines()) + countXmasInLines(problem.verticalLines())
               + countXmasInLines(problem.diagonalDownLines()) + countXmasInLines(problem.diagonalUpLines());
    }

    static long countXmasInLines(List<String> lines) {
        return lines.stream().mapToLong(Day04::countXmas).sum();
    }

    static long countXmas(String line) {
        return XMAS.matcher(line).results().count() + XMAS.matcher(reverseString(line)).results().count();
    }

    static String reverseString(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    static long solvePart2(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        int xmasCount = 0;
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (isXMAS(row, col, lines)) {
                    xmasCount++;
                }
            }
        }
        return xmasCount;
    }

    static boolean isXMAS(int row, int col, List<String> lines) {
        Cross result = getCross(row, col, lines);
        return ("MAS".equals(result.upStr()) || "SAM".equals(result.upStr())) && ("MAS".equals(result.downStr()) || "SAM".equals(result.downStr()));
    }

    static Cross getCross(int row, int col, List<String> lines) {
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        for (int delta = -1; delta <= 1; delta++) {
            up.append(getCharAt(row - delta, col + delta, lines));
            down.append(getCharAt(row + delta, col + delta, lines));
        }
        return new Cross(up.toString(), down.toString());
    }

    record Cross(String upStr, String downStr) {
    }

    static char getCharAt(int row, int col, List<String> lines) {
        return lines.get(row).charAt(col);
    }
}

record Position(int row, int col) {

}
