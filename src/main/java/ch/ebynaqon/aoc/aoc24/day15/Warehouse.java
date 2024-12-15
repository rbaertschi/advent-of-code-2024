package ch.ebynaqon.aoc.aoc24.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Warehouse {
    private final boolean[][] obstacles;
    private final int[][] boxes;

    public Warehouse(boolean[][] obstacles, int[][] boxes) {
        this.obstacles = obstacles;
        this.boxes = boxes;
    }

    public List<Position> boxes() {
        List<Position> boxPositions = new ArrayList<>();
        for (int y = 0; y < boxes.length; y++) {
            for (int x = 0; x < boxes[y].length; x++) {
                if (boxes[y][x] > 0) {
                    boxPositions.add(new Position(x, y));
                }
            }
        }
        return boxPositions;
    }

    public void print() {
        char[][] map = new char[obstacles.length][obstacles[0].length];
        for (int y = 0; y < obstacles.length; y++) {
            for (int x = 0; x < obstacles[y].length; x++) {
                if (obstacles[y][x]) {
                    map[y][x] = '#';
                } else if (boxes[y][x] > 0) {
                    map[y][x] = 'O';
                } else {
                    map[y][x] = '.';
                }
            }
        }
        for (char[] line : map) {
            System.out.print(line);
            System.out.println();
        }
    }

    public boolean isBlocked(List<Position> positionsToCheck) {
        for (Position position : positionsToCheck) {
            if (obstacles[position.y()][position.x()]) {
                return true;
            }
        }
        return false;
    }

    public boolean isFree(List<Position> positionsToCheck) {
        for (Position position : positionsToCheck) {
            if (obstacles[position.y()][position.x()]) {
                return false;
            } else if (boxes[position.y()][position.x()] > 0) {
                return false;
            }
        }
        return true;
    }

    public List<Box> liftBoxes(List<Position> positions) {
        List<Box> liftedBoxes = new ArrayList<>();
        for (Position position : positions) {
            int index = boxes[position.y()][position.x()];
            boxes[position.y()][position.x()] = 0;
            Optional<Box> existingBox = liftedBoxes.stream().filter(box -> box.index() == index).findFirst();
            if (existingBox.isPresent()) {
                Box box = existingBox.get();
                box.positions().add(position);
            } else {
                ArrayList<Position> boxPositions = new ArrayList<>();
                boxPositions.add(position);
                liftedBoxes.add(new Box(index, boxPositions));
            }
        }
        for (Box box : liftedBoxes) {
            for (Position position : box.positions()) {
                if (boxes[position.y()][position.x() + 1] == box.index()) {
                    boxes[position.y()][position.x() + 1] = 0;
                    box.positions().add(new Position(position.x() + 1, position.y()));
                }
                if (boxes[position.y()][position.x() - 1] == box.index()) {
                    boxes[position.y()][position.x() - 1] = 0;
                    box.positions().add(new Position(position.x() - 1, position.y()));
                }
            }
        }
        return liftedBoxes;
    }

    public void placeBox(Box box) {
        for (Position position : box.positions()) {
            boxes[position.y()][position.x()] = box.index();
        }
    }
}
