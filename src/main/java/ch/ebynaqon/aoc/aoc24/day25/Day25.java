package ch.ebynaqon.aoc.aoc24.day25;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day25 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] locksAndKeys = input.getWholeInput().split("\\n\\n");
        List<Lock> locks = new ArrayList<>();
        List<Key> keys = new ArrayList<>();
        int height = 0;
        for (String lockOrKey : locksAndKeys) {
            String[] lines = lockOrKey.split("\\n");
            height = lines.length - 2;
            if (lines[0].startsWith("#")) {
                locks.add(new Lock(parseColumns(lines, '.')));
            } else {
                keys.add(new Key(parseColumns(lines, '#')));
            }
        }
        return new ProblemInput(locks, keys, height);
    }

    private static List<Integer> parseColumns(String[] lines, char stopCharacter) {
        List<Integer> columns = new ArrayList<>();
        for (int column = 0; column < lines[0].length(); column++) {
            int columnHeight = -1;
            for (int row = 1; row < lines.length - 1 && columnHeight == -1; row++) {
                if (lines[row].charAt(column) == stopCharacter) {
                    columnHeight = row - 1;
                }
            }
            columns.add(columnHeight == -1 ? lines.length - 2 : columnHeight);
        }
        return columns;
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        int result = 0;
        for (Lock lock : problem.locks()) {
            for (Key key : problem.keys()) {
                if (!hasOverlap(lock, key)) {
                    result++;
                }
            }
        }
        return result;
    }

    static boolean hasOverlap(Lock lock, Key key) {
        for (int column = 0; column < lock.column().size(); column++) {
            if (lock.column().get(column) > key.column().get(column)) {
                return true;
            }
        }
        return false;
    }
}

record ProblemInput(List<Lock> locks, List<Key> keys, int height) {
}

record Lock(List<Integer> column) {
}

record Key(List<Integer> column) {
}
