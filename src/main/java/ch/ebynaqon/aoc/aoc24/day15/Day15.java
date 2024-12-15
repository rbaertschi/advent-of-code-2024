package ch.ebynaqon.aoc.aoc24.day15;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day15 {

    boolean PRINT = false;

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] mapAndMovements = input.getWholeInput().split("\\n\\n");
        String[] mapLines = mapAndMovements[0].split("\\n");
        Position robot = null;
        boolean[][] obstacles = new boolean[mapLines.length][mapLines[0].length()];
        boolean[][] boxes = new boolean[mapLines.length][mapLines[0].length()];
        for (int y = 0; y < mapLines.length; y++) {
            String[] line = mapLines[y].split("");
            for (int x = 0; x < line.length; x++) {
                switch (line[x]) {
                    case "#" -> obstacles[y][x] = true;
                    case "@" -> robot = new Position(x, y);
                    case "O" -> boxes[y][x] = true;
                    default -> {
                        // assuming blank space; nothing to be done
                    }
                }
            }
        }
        List<Movement> movements = Arrays.stream(mapAndMovements[1].replaceAll("\\n","").split("")).map(Movement::from).toList();
        return new ProblemInput(robot, movements, new Warehouse(obstacles, boxes));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        Position robotPosition = problem.robotPosition();
        Warehouse warehouse = problem.warehouse();
        for (Movement movement : problem.movements()) {
            robotPosition = tryMove(robotPosition, movement, warehouse);
        }
        if (PRINT) {
            warehouse.print();
        }
        return warehouse.boxes().stream().mapToInt(position -> position.x() + 100 * position.y()).sum();
    }

    static Position tryMove(Position robot, Movement movement, Warehouse warehouse) {
        Position nextPosition = robot.move(movement);
        int packagesMoving = 0;
        while (warehouse.hasBoxAt(nextPosition)) {
            warehouse.removeBoxAt(nextPosition);
            packagesMoving++;
            nextPosition = nextPosition.move(movement);
        }
        if (warehouse.hasObstacleAt(nextPosition)) {
            nextPosition = nextPosition.move(movement.reverse());
        }
        for (int i = 0; i < packagesMoving; i++) {
            warehouse.putBoxAt(nextPosition);
            nextPosition = nextPosition.move(movement.reverse());
        }
        return nextPosition;
    }


    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

