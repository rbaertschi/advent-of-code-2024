package ch.ebynaqon.aoc.aoc24.day15;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Day15 {

    boolean PRINT = false;

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] mapAndMovements = input.getWholeInput().split("\\n\\n");
        String[] mapLines = mapAndMovements[0].split("\\n");
        Position robot = null;
        boolean[][] obstacles = new boolean[mapLines.length][mapLines[0].length()];
        int[][] boxes = new int[mapLines.length][mapLines[0].length()];
        int boxIndex = 1;
        for (int y = 0; y < mapLines.length; y++) {
            String[] line = mapLines[y].split("");
            for (int x = 0; x < line.length; x++) {
                switch (line[x]) {
                    case "#" -> obstacles[y][x] = true;
                    case "@" -> robot = new Position(x, y);
                    case "O" -> boxes[y][x] = boxIndex++;
                    default -> {
                        // assuming blank space; nothing to be done
                    }
                }
            }
        }
        List<Movement> movements = Arrays.stream(mapAndMovements[1].replaceAll("\\n", "").split("")).map(Movement::from).toList();
        return new ProblemInput(robot, movements, new Warehouse(obstacles, boxes));
    }

    static long solvePart1(RawProblemInput input) {
        return solve(parseProblem(input));
    }

    private static int solve(ProblemInput problem) {
        Position robotPosition = problem.robotPosition();
        Warehouse warehouse = problem.warehouse();
        for (Movement movement : problem.movements()) {
            robotPosition = tryMove(robotPosition, movement, warehouse);
        }
        if (PRINT) {
            warehouse.print(robotPosition);
        }
        return warehouse.boxes().stream().mapToInt(position -> position.x() + 100 * position.y()).sum();
    }

    static Position tryMove(Position robot, Movement movement, Warehouse warehouse) {
        List<Position> pushingPositions = new ArrayList<>();
        pushingPositions.add(robot.move(movement));
        List<Box> boxesToMove = new ArrayList<>();
        while (!warehouse.isBlocked(pushingPositions) && !warehouse.isFree(pushingPositions)) {
            List<Box> newBoxes = warehouse.liftBoxes(pushingPositions);
            boxesToMove.addAll(newBoxes);
            pushingPositions = newBoxes.stream().flatMap((Box box) -> box.pushingPositions(movement).stream()).toList();
        }
        if (warehouse.isFree(pushingPositions)) {
            // place boxes shifted in direction of movement from where they were picked up
            for (Box box : boxesToMove) {
                warehouse.placeBox(box.move(movement));
            }
            return robot.move(movement);
        } else {
            // place boxes where they were picked up
            for (Box box : boxesToMove) {
                warehouse.placeBox(box);
            }
        }
        return robot;
    }


    static long solvePart2(RawProblemInput input) {
        return solve(makeDoubleWide(parseProblem(input)));
    }

    static ProblemInput makeDoubleWide(ProblemInput problem) {
        return new ProblemInput(
                new Position(problem.robotPosition().x() * 2, problem.robotPosition().y()),
                problem.movements(),
                problem.warehouse().doubleWidth()
        );
    }
}

