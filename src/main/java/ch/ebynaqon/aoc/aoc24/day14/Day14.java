package ch.ebynaqon.aoc.aoc24.day14;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day14 {

    boolean PRINT_RESULT = false;

    static ProblemInput parseProblem(RawProblemInput input, int width, int height) {
        List<Robot> robots = input.getLines().stream().map(Day14::parseLine).toList();
        return new ProblemInput(robots, width, height);
    }

    private static Robot parseLine(String input) {
//        p=6,3 v=-1,-3
        String[] posAndVelParts = input.split("\\s*v=");
        String[] positionParts = posAndVelParts[0].substring(2).split(",");
        String[] velocityParts = posAndVelParts[1].split(",");
        Position position = new Position(Integer.parseInt(positionParts[0]), Integer.parseInt(positionParts[1]));
        Velocity velocity = new Velocity(Integer.parseInt(velocityParts[0]), Integer.parseInt(velocityParts[1]));
        return new Robot(position, velocity);
    }

    static int solvePart1(RawProblemInput input, int width, int height, int seconds) {
        ProblemInput problem = parseProblem(input, width, height);
        ArrayList<Position> finalPositions = new ArrayList<>();
        for (Robot robot : problem.robots()) {
            Position finalPosition = move(robot.position(), robot.velocity(), seconds, width, height);
            finalPositions.add(finalPosition);
        }
        int[] sectorCounts = getSectorCounts(finalPositions, width, height);
        return sectorCounts[0] * sectorCounts[1] * sectorCounts[2] * sectorCounts[3];
    }

    private static int[] getSectorCounts(List<Position> positions, int width, int height) {
        int[] sectorCounts = new int[4];
        int verticalLine = (width - 1) / 2;
        int horizontalLine = (height - 1) / 2;
        for (Position finalPosition : positions) {
            if (finalPosition.x() < verticalLine) {
                if (finalPosition.y() < horizontalLine) {
                    // top left
                    sectorCounts[0]++;
                } else if (finalPosition.y() > horizontalLine) {
                    // bottom left
                    sectorCounts[2]++;
                }
            } else if (finalPosition.x() > verticalLine) {
                if (finalPosition.y() < horizontalLine) {
                    // top right
                    sectorCounts[1]++;
                } else if (finalPosition.y() > horizontalLine) {
                    // bottom right
                    sectorCounts[3]++;
                }
            }
        }
        return sectorCounts;
    }

    static Position move(Position position, Velocity velocity, int seconds, int width, int height) {
        for (int i = 0; i < seconds; i++) {
            position = position.moveWithTeleportation(velocity, width, height);
        }
        return position;
    }

    static long solvePart2(RawProblemInput input, int width, int height) {
        ProblemInput problem = parseProblem(input, width, height);
        List<Position> robotPositions = new ArrayList<>(problem.robots().stream().map(Robot::position).toList());
        int seconds = 0;
        while (true) {
            seconds++;
            for (int i = 0; i < robotPositions.size(); i++) {
                Position nextPosition = move(robotPositions.get(i), problem.robots().get(i).velocity(), 1, width, height);
                robotPositions.set(i, nextPosition);
            }
            int stats = numberOfMirroredPoints(robotPositions, width, height);
            if (stats > 200) {
                if (PRINT_RESULT) {
                    System.out.println("========== Seconds: " + seconds + " ==========");
                    System.out.println(stats);
                    print(robotPositions, width, height);
                }
                return seconds;
            }
        }
    }

    static int numberOfMirroredPoints(List<Position> robotPositions, int width, int height) {
        List<List<Integer>> xValuesByRow = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            xValuesByRow.add(new ArrayList<>());
        }
        for (Position position : robotPositions) {
            xValuesByRow.get(position.y()).add(position.x());
        }
        int[] averages = new int[height];
        int average = 0;
        for (int y = 0; y < height; y++) {
            List<Integer> xValues = xValuesByRow.get(y);
            int sum = 0;
            int count = 0;
            for (int x : xValues) {
                sum += x;
                count++;
            }
            if (count > 0) {
                averages[y] = sum / count;
                average += averages[y];
            }
        }
        average /= height;
        int mirroredPoints = 0;
        for (int y = 0; y < height; y++) {
            List<Integer> xs = xValuesByRow.get(y);
            for (int x : xs) {
                int mirrorX = average + (average - x);
                for (int otherX : xs) {
                    if (x != otherX && Math.abs(otherX - mirrorX) < 1) {
                        mirroredPoints++;
                    }
                }
            }
        }

        return mirroredPoints;
    }

    static void print(List<Position> positions, int width, int height) {
        int[][] counts = new int[width][height];
        for (Position position : positions) {
            counts[position.x()][position.y()]++;
        }
        StringBuilder b = new StringBuilder(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int count = counts[x][y];
                if (count > 0) {
                    b.append("x");
                } else {
                    b.append(" ");
                }
            }
            b.append("\n");
        }
        System.out.println(b);
    }
}
