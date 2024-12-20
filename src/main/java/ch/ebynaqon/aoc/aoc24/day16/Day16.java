package ch.ebynaqon.aoc.aoc24.day16;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static ch.ebynaqon.aoc.aoc24.day16.Direction.East;
import static ch.ebynaqon.aoc.aoc24.day16.Direction.South;
import static ch.ebynaqon.aoc.aoc24.day16.Direction.West;

interface Day16 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        boolean[][] obstacles = new boolean[rows][cols];
        int[][][] costs = new int[Direction.values().length][rows][cols];
        Position start = null;
        Position end = null;
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                for (Direction direction : Direction.values()) {
                    costs[direction.ordinal()][row][col] = Integer.MAX_VALUE;
                }
                switch (line.charAt(col)) {
                    case 'S' -> start = new Position(row, col);
                    case 'E' -> end = new Position(row, col);
                    case '#' -> obstacles[row][col] = true;
                }
            }
        }
        return new ProblemInput(obstacles, costs, start, end);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        problem.setCost(problem.start(), East, 0);
        return solve(problem);
    }

    private static int solve(ProblemInput problem) {
        Queue<ScoredPosition> toCheck = new PriorityQueue<>();
        toCheck.add(new ScoredPosition(0, problem.start()));
        while (!toCheck.isEmpty()) {
            ScoredPosition scoredPosition = toCheck.poll();
            Position currentPosition = scoredPosition.position();
            if (currentPosition.equals(problem.end())) {
                return scoredPosition.cost();
            }
            for (Direction direction : Direction.values()) {
                Position nextPosition = new Position(currentPosition.row() + direction.deltaRow(), currentPosition.col() + direction.deltaCol());
                if (!problem.hasObstacleAt(nextPosition)) {
                    int cost = problem.getCost(currentPosition, direction) + 1;
                    int updatedCost = problem.setCost(nextPosition, direction, cost);
                    if (updatedCost < Integer.MAX_VALUE) {
                        toCheck.add(new ScoredPosition(updatedCost, nextPosition));
                    }
                }
            }
        }
        throw new IllegalStateException("Failed to find a solution to the maze.");
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        problem.setCost(problem.start(), East, 0);
        int solution = solve(problem);
        ProblemInput reverseProblem = problem.reverse();
        reverseProblem.setCost(reverseProblem.start(), South, 0);
        reverseProblem.setCost(reverseProblem.start(), West, 0);
        solve(reverseProblem);
        List<Position> positionsAlongCheapestPaths = new ArrayList<>();
        for (int row = 0; row < problem.rows(); row++) {
            for (int col = 0; col < problem.cols(); col++) {
                Position position = new Position(row, col);
                int minCost = Integer.MAX_VALUE;
                for (Direction direction : Direction.values()) {
                    int cost = problem.getCost(position, direction) + reverseProblem.getCost(position, direction.opposite());
                    minCost = Math.min(minCost, cost);
                }
                if (minCost == solution) {
                    positionsAlongCheapestPaths.add(position);
                }
            }
        }
        return positionsAlongCheapestPaths.size();
    }
}

record ScoredPosition(int cost, Position position) implements Comparable<ScoredPosition> {
    @Override
    public int compareTo(ScoredPosition o) {
        return Comparator.comparingInt(ScoredPosition::cost).compare(this, o);
    }
}
