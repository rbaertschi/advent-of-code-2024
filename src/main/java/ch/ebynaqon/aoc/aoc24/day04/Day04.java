package ch.ebynaqon.aoc.aoc24.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

interface Day04 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        return new ProblemInput(lines, rows, cols);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        AtomicInteger xmasCount = new AtomicInteger(0);
        problem.visitEachPosition((row, col) ->
                xmasCount.addAndGet(numberOfXmasStartingAtPosition(row, col, problem)));
        return xmasCount.get();
    }

    static int numberOfXmasStartingAtPosition(int row, int col, ProblemInput problem) {
        int count = 0;
        for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                if (!(deltaRow == 0 && deltaCol == 0)) {
                    if (isXmasString(row, col, deltaRow, deltaCol, problem)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    static boolean isXmasString(int row, int col, int deltaRow, int deltaCol, ProblemInput problem) {
        StringBuilder string = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            string.append(problem.getCharAt(row + i * deltaRow, col + i * deltaCol));
        }
        return "XMAS".contentEquals(string);
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        AtomicInteger xmasCount = new AtomicInteger(0);
        problem.visitEachPosition((row, col) -> {
            if (isXMAS(row, col, problem)) {
                xmasCount.incrementAndGet();
            }
        });
        return xmasCount.get();
    }

    static boolean isXMAS(int row, int col, ProblemInput problem) {
        Cross result = getCross(row, col, problem);
        return ("MAS".equals(result.upStr()) || "SAM".equals(result.upStr())) && ("MAS".equals(result.downStr()) || "SAM".equals(result.downStr()));
    }

    static Cross getCross(int row, int col, ProblemInput problem) {
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        for (int delta = -1; delta <= 1; delta++) {
            up.append(problem.getCharAt(row - delta, col + delta));
            down.append(problem.getCharAt(row + delta, col + delta));
        }
        return new Cross(up.toString().trim(), down.toString().trim());
    }

    record Cross(String upStr, String downStr) {
    }
}

