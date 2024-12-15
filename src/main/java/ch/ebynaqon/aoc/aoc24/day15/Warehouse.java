package ch.ebynaqon.aoc.aoc24.day15;

import java.util.ArrayList;
import java.util.List;

class Warehouse {
    private final boolean[][] obstacles;
    private final boolean[][] boxes;

    public Warehouse(boolean[][] obstacles, boolean[][] boxes) {
        this.obstacles = obstacles;
        this.boxes = boxes;
    }

    public boolean hasBoxAt(Position position) {
        return boxes[position.y()][position.x()];
    }

    public void removeBoxAt(Position position) {
        boxes[position.y()][position.x()] = false;
    }

    public void putBoxAt(Position position) {
        boxes[position.y()][position.x()] = true;
    }

    public boolean hasObstacleAt(Position position) {
        return obstacles[position.y()][position.x()];
    }

    public List<Position> boxes() {
        List<Position> boxPositions = new ArrayList<>();
        for (int y = 0; y < boxes.length; y++) {
            for (int x = 0; x < boxes[y].length; x++) {
                if (boxes[y][x]) {
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
                } else if (boxes[y][x]) {
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
}
