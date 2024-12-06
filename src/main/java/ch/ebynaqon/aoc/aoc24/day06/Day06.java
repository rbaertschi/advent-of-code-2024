package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    static long solvePart1(RawProblemInput input) throws LoopDetected {
        ProblemInput problem = parseProblem(input);
        GuardPath result = getGuardPath(problem);
        return result.visitedPositions().size();
    }

    static long solvePart2(RawProblemInput input) throws LoopDetected {
        ProblemInput problem = parseProblem(input);
        GuardPath guardPath = getGuardPath(problem);
        return loopPositions(guardPath, problem).size();
    }

    static List<Position> loopPositions(GuardPath guardPath, ProblemInput problem) {
        ArrayList<Position> newObstaclePositions = new ArrayList<>();
        List<WalkedPosition> path = guardPath.path();
        ArrayList<Position> alreadyWalkedPositions = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            WalkedPosition currentPosition = path.get(i);
            WalkedPosition potentialObstaclePosition = path.get(i + 1);
            if (!alreadyWalkedPositions.contains(potentialObstaclePosition.position())) {
                Direction direction = Direction.fromTo(currentPosition.position(), potentialObstaclePosition.position());
                ArrayList<Position> obstaclesToCheck = new ArrayList<>(problem.obstacles());
                obstaclesToCheck.add(potentialObstaclePosition.position());
                try {
                    Guard guard = new Guard(currentPosition.position(), direction.turn());
                    getGuardPath(new ProblemInput(guard, obstaclesToCheck, problem.rows(), problem.cols()));
                } catch (LoopDetected e) {
                    newObstaclePositions.add(potentialObstaclePosition.position());
                }
            }
            alreadyWalkedPositions.add(currentPosition.position());
        }
        return newObstaclePositions;
    }

    private static GuardPath getGuardPath(ProblemInput problem) throws LoopDetected {
        Guard guard = problem.guard();
        Position obstacle = problem.getObstacleFacedBy(guard);
        GuardPath path = new GuardPath(new ArrayList<>());
        path.add(new WalkedPosition(guard.position(), mutableSetOf(guard.direction())));
        while (obstacle != null) {
            Direction currentDirection = guard.direction();
            path.addDirectionToLastPosition(currentDirection);
            List<Position> walkedPositions = guard.walkToObstacle(obstacle);
            path.addAll(walkedPositions.subList(1, walkedPositions.size()).stream()
                    .map(pos -> new WalkedPosition(pos, mutableSetOf(currentDirection))).toList());
            Position nextGuardPosition = walkedPositions.getLast();
            Direction nextDirection = currentDirection.turn();
            guard = new Guard(nextGuardPosition, nextDirection);
            if (path.alreadyWalked(guard.position(), guard.direction())) {
                throw new LoopDetected();
            }
            obstacle = problem.getObstacleFacedBy(guard);
        }
        Direction currentDirection = guard.direction();
        List<Position> walkedPositions = guard.walkToObstacle(problem.getEdgeFacedBy(guard));
        path.addAll(walkedPositions.subList(1, walkedPositions.size()).stream()
                .map(pos -> new WalkedPosition(pos, mutableSetOf(currentDirection))).toList());
        return path;
    }

    private static Set<Direction> mutableSetOf(Direction currentDirection) {
        HashSet<Direction> result = new HashSet<>();
        result.add(currentDirection);
        return result;
    }

}

record WalkedPosition(Position position, Set<Direction> directions) {
}

record GuardPath(List<WalkedPosition> path) {
    HashSet<Position> visitedPositions() {
        return new HashSet<>(path.stream().map(WalkedPosition::position).toList());
    }

    public void add(WalkedPosition walkedPosition) {
        path.add(walkedPosition);
    }

    public void addAll(List<WalkedPosition> positions) {
        path.addAll(positions);
    }

    public boolean alreadyWalked(Position position, Direction direction) {
        return path.stream().anyMatch(pos -> pos.position().equals(position) && pos.directions().contains(direction));
    }

    public void addDirectionToLastPosition(Direction direction) {
        path().getLast().directions().add(direction);
    }
}

class LoopDetected extends Exception {
}
