package ch.ebynaqon.aoc.aoc24.day02;

import ch.ebynaqon.aoc.helper.CollectionHelper;

import java.util.List;

record Report(List<Integer> levels) {
    public boolean isSafe() {
        return safetyCheck(levels);
    }

    private static boolean safetyCheck(List<Integer> levels) {
        var lastLevel = levels.getFirst();
        Boolean mustBeIncreasing = null;
        for (int i = 1; i < levels.size(); i++) {
            var currentLevel = levels.get(i);
            var difference = currentLevel - lastLevel;
            var isIncreasing = difference > 0;
            if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
                return false;
            }
            if (mustBeIncreasing == null) {
                mustBeIncreasing = isIncreasing;
            } else {
                if (mustBeIncreasing != isIncreasing) {
                    return false;
                }
            }
            lastLevel = currentLevel;
        }
        return true;
    }

    public boolean isSafeWithDampener() {
        if (safetyCheck(levels)) {
            return true;
        }
        for (int i = 0; i < levels.size(); i++) {
            if (safetyCheck(CollectionHelper.dropValueAtIndex(levels, i))) {
                return true;
            }
        }
        return false;
    }
}
