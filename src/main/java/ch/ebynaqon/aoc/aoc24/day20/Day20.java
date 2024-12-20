package ch.ebynaqon.aoc.aoc24.day20;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day20 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        boolean[][] obstacles = new boolean[rows][cols];
        Position start = null, end = null;
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                switch (line.charAt(col)) {
                    case '#' -> obstacles[row][col] = true;
                    case 'S' -> start = new Position(row, col);
                    case 'E' -> end = new Position(row, col);
                    default -> { /* free space */ }
                }
            }
        }
        return new ProblemInput(obstacles, start, end, rows, cols);
    }

    static long solvePart1(RawProblemInput input, int timeSavedThreshold) {
        ProblemInput problem = parseProblem(input);
        List<TrackPosition> track = computeRaceTrack(problem);
        return countCheats(2, timeSavedThreshold, track);
    }

    static long solvePart2(RawProblemInput input, int timeSavedThreshold) {
        ProblemInput problem = parseProblem(input);
        List<TrackPosition> track = computeRaceTrack(problem);
        return countCheats(20, timeSavedThreshold, track);
    }

    static List<TrackPosition> computeRaceTrack(ProblemInput problem) {
        boolean[][] visited = new boolean[problem.rows()][problem.cols()];
        List<TrackPosition> reverseTrack = new ArrayList<>();
        TrackPosition current = new TrackPosition(problem.end(), 0);
        visited[current.position().row()][current.position().col()] = true;
        reverseTrack.add(current);
        while (!current.position().equals(problem.start())) {
            TrackPosition last = current;
            for (Position nextPosition : current.position().neighbours()) {
                if (!problem.isObstructed(nextPosition) && !visited[nextPosition.row()][nextPosition.col()]) {
                    TrackPosition next = new TrackPosition(nextPosition, current.timeToEnd() + 1);
                    reverseTrack.add(next);
                    current = next;
                    visited[nextPosition.row()][nextPosition.col()] = true;
                    break;
                }
            }
            if (current.equals(last)) {
                throw new IllegalStateException("Stuck at the same location! Could not find path forward!");
            }
        }
        return reverseTrack.reversed();
    }

    private static int countCheats(int maxCheatDuration, int timeSavedThreshold, List<TrackPosition> track) {
        int numberOfCheats = 0;
        for (int i = 0; i < track.size() - 1; i++) {
            TrackPosition cheatStart = track.get(i);
            for (int j = i + 1; j < track.size(); j++) {
                TrackPosition cheatEnd = track.get(j);
                int durationOfCheat = cheatStart.distance(cheatEnd);
                if (durationOfCheat <= maxCheatDuration) {
                    int timeSaved = cheatStart.timeToEnd() - (cheatEnd.timeToEnd() + durationOfCheat);
                    if (timeSaved >= timeSavedThreshold) {
                        numberOfCheats++;
                    }
                }
            }
        }
        return numberOfCheats;
    }
}


record ProblemInput(boolean[][] obstacles, Position start, Position end, int rows, int cols) {
    public boolean isObstructed(Position position) {
        return obstacles[position.row()][position.col()];
    }
}

record TrackPosition(Position position, int timeToEnd) {
    public int distance(TrackPosition to) {
        return position.distance(to.position());
    }
}

record Position(int row, int col) {
    public List<Position> neighbours() {
        return List.of(
                new Position(row + 1, col),
                new Position(row - 1, col),
                new Position(row, col + 1),
                new Position(row, col - 1)
        );
    }

    public int distance(Position to) {
        return Math.abs(to.row() - row) + Math.abs(to.col() - col);
    }
}
