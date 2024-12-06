package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

interface Day06 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        ArrayList<Position> obstacles = new ArrayList<>();
        Guard guard = null;
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                switch (line.charAt(col)) {
                    case '#' -> obstacles.add(new Position(row, col));
                    case '^' -> guard = new Guard(new Position(row, col), Direction.UP);
                }
            }
        }
        return new ProblemInput(guard, obstacles, rows, cols);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        Guard guard = problem.guard();
        HashSet<Position> visitedPositions = new HashSet<>();
        Position obstacle = problem.getObstacleFacedBy(guard);
        while (obstacle != null) {
            List<Position> walkedPositions = guard.walkToObstacle(obstacle);
            Position nextGuardPosition = walkedPositions.getLast();
            visitedPositions.addAll(walkedPositions);
            guard = new Guard(nextGuardPosition, guard.direction().turn());
            obstacle = problem.getObstacleFacedBy(guard);
        }
        visitedPositions.addAll(guard.walkToObstacle(problem.getEdgeFacedBy(guard)));

        return visitedPositions.size();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.obstacles().stream().mapToLong(Position::row).max().orElseThrow();
    }
}

