package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;

interface Day06 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        ProblemInput problem = new ProblemInput(rows, cols);
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                switch (line.charAt(col)) {
                    case '#' -> problem.addObstacle(row, col);
                    case '^' -> problem.setGuard(new Guard(new Position(row, col), Direction.UP));
                }
            }
        }
        return problem;
    }

    static long solvePart1(RawProblemInput input) throws LoopDetectedException {
        return getGuardWalkLength(parseProblem(input));
    }

    static long solvePart2(RawProblemInput input) throws LoopDetectedException {
        ProblemInput problem = parseProblem(input);
        Position guardPosition = problem.guard().position();
        int loopCount = 0;
        for (int row = 0; row < problem.rows(); row++) {
            for (int col = 0; col < problem.cols(); col++) {
                if ((row != guardPosition.row() || col != guardPosition.column()) && !problem.isObstacle(row, col)) {
                    problem.addObstacle(row, col);
                    try {
                        getGuardWalkLength(problem);
                    } catch (LoopDetectedException e) {
                        loopCount++;
                    }
                    problem.clearObstacle(row, col);
                }
            }
        }
        return loopCount;
    }

    private static int getGuardWalkLength(ProblemInput problem) throws LoopDetectedException {
        var guard = problem.guard();
        var visited = problem.visited();
        visited.reset();
        visited.add(guard.position());
        while (guard != null) {
            Direction currentDirection = guard.direction();
            var nextGuardPosition = problem.walkToNextObstacle(guard, visited::add, visited::addWaypoint);
            if (nextGuardPosition != null) {
                guard = new Guard(nextGuardPosition, currentDirection.turn());
                if (visited.alreadyWalked(guard.position(), guard.direction())) {
                    throw new LoopDetectedException();
                }
            } else {
                guard = null;
            }
        }
        return visited.getLength();
    }

}

