package ch.ebynaqon.aoc.aoc24.day18;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface Day18 {

    static ProblemInput parseProblem(RawProblemInput input, int width, int height) {
        List<Position> samples = input.getLines().stream().map(Day18::parseLine).toList();
        return new ProblemInput(samples, width, height);
    }

    private static Position parseLine(String input) {
        String[] parts = input.split(",");
        return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    static long solvePart1(RawProblemInput input, int width, int height, int numberOfDrops) {
        ProblemInput problem = parseProblem(input, width, height);
        boolean[][] obstacles = createObstaclesByteMap(width, height, problem.dropLocations().subList(0, numberOfDrops));
        boolean[][] visited = new boolean[height][width];
        ByteMap map = new ByteMap(width, height, obstacles, visited);
        Position start = new Position(0, 0);
        Position end = new Position(width - 1, height - 1);
        Queue<Queued> toCheck = new LinkedList<>();
        toCheck.add(new Queued(start, 0));
        visited[start.y()][start.x()] = true;
        while (!toCheck.isEmpty()) {
            Queued current = toCheck.poll();
            Position currentPosition = current.position();
            int distance = current.distance();
            if (currentPosition.equals(end)) {
                return distance;
            }
            for (Position position : currentPosition.neighbours()) {
                if (map.isWithinBounds(position)) {
                    if (!obstacles[position.y()][position.x()] && !visited[position.y()][position.x()]) {
                        toCheck.add(new Queued(position, distance + 1));
                        visited[position.y()][position.x()] = true;
                    }
                }
            }
        }
        throw new IllegalStateException("Failed to find path from start to end!");
    }

    private static boolean[][] createObstaclesByteMap(int width, int height, List<Position> positions) {
        boolean[][] obstacles = new boolean[height][width];
        for (Position position : positions) {
            obstacles[position.y()][position.x()] = true;
        }
        return obstacles;
    }

    static int minDistanceFrom(Position current, Position end, ByteMap map) {
        int distance = Integer.MAX_VALUE;
        for (Position position : current.neighbours()) {
            if (map.isWithinBounds(position) && !map.hasObstacleAt(position) && !map.wasVisited(position)) {

            }
        }
        return distance;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input, 71, 71);
        return 0;
    }
}

record Queued(Position position, int distance) {
}

record ByteMap(int width, int height, boolean[][] obstacles, boolean[][] visited) {
    public boolean isWithinBounds(Position position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    public boolean hasObstacleAt(Position position) {
        return obstacles[position.y()][position.x()];
    }

    public boolean wasVisited(Position position) {
        return false;
    }
}
