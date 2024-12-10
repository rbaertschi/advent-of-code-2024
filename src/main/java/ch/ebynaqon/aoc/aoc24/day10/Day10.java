package ch.ebynaqon.aoc.aoc24.day10;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

interface Day10 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int columns = lines.getFirst().length();
        int[][] heightMap = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int column = 0; column < columns; column++) {
                int symbol = (line.charAt(column) - '0');
                heightMap[row][column] = symbol;
            }
        }

        return new ProblemInput(rows, columns, heightMap);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return solveWithScoreMapper(problem, peaks -> new HashSet<>(peaks).size());
    }

    private static int solveWithScoreMapper(ProblemInput problem, Function<List<Position>, Integer> scoreMapper) {
        Map<Position, List<Position>> peaksReachable = new HashMap<>();
        int result = 0;
        for (int row = 0; row < problem.rows(); row++) {
            for (int column = 0; column < problem.cols(); column++) {
                Position position = new Position(row, column);
                if (problem.getHeight(position) == 0) {
                    result += scoreMapper.apply(peaksReachableFrom(position, problem, peaksReachable));
                }
            }
        }
        return result;
    }

    static List<Position> peaksReachableFrom(Position position, ProblemInput problem, Map<Position, List<Position>> peaksReachable) {
        if (peaksReachable.containsKey(position)) {
            return peaksReachable.get(position);
        }
        List<Position> result = new ArrayList<>();
        int currentHeight = problem.getHeight(position);
        if (currentHeight == 9) {
            result.add(position);
        } else {
            for (Direction direction : Direction.values()) {
                Position nextPosition = position.move(direction);
                if (problem.isWithinBounds(nextPosition)) {
                    if (problem.getHeight(nextPosition) == currentHeight + 1) {
                        result.addAll(peaksReachableFrom(nextPosition, problem, peaksReachable));
                    }
                }
            }
        }
        peaksReachable.put(position, result);
        return result;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return solveWithScoreMapper(problem, List::size);
    }
}

record Position(int row, int col) {
    public Position move(Direction direction) {
        return new Position(row + direction.getDeltaRow(), col + direction.getDeltaCol());
    }
}

enum Direction {
    UP(-1,0), DONWN(1,0), LEFT(0,-1), RIGHT(0,1);

    private final int deltaRow;
    private final int deltaCol;

    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaCol() {
        return deltaCol;
    }
}
