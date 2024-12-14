package ch.ebynaqon.aoc.aoc24.day14;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;

interface Day14 {

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
        int verticalLine = (width - 1) / 2;
        int horizontalLine = (height - 1) / 2;
        int[] sectorCounts = new int[4];
        for (Robot robot : problem.robots()) {
            Position finalPosition = move(robot, seconds, width, height);
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
        return sectorCounts[0] * sectorCounts[1] * sectorCounts[2] * sectorCounts[3];
    }

    static Position move(Robot robot, int seconds, int width, int height) {
        Position position = robot.position();
        Velocity velocity = robot.velocity();
        for (int i = 0; i < seconds; i++) {
            position = position.moveWithTeleportation(velocity, width, height);
        }
        return position;
    }

    static long solvePart2(RawProblemInput input, int width, int height) {
        ProblemInput problem = parseProblem(input, width, height);
        return 0;
    }
}

