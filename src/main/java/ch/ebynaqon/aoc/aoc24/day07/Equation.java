package ch.ebynaqon.aoc.aoc24.day07;

import java.util.List;

record Equation(long testValue, List<Long> operands) {
    public boolean canBeSolved() {
        return canSolve(testValue, 0L, operands);
    }

    private boolean canSolve(long testValue, long currentValue, List<Long> operands) {
        if (operands.isEmpty()) {
            return testValue == currentValue;
        }
        if (currentValue > testValue) {
            return false;
        }
        return canSolve(testValue, currentValue + operands.getFirst(), operands.subList(1, operands.size()))
               || canSolve(testValue, currentValue * operands.getFirst(), operands.subList(1, operands.size()));
    }

    public boolean canBeSolvedWithConcat() {
        return canSolveWithConcat(testValue, 0L, operands);
    }

    private boolean canSolveWithConcat(long testValue, long currentValue, List<Long> operands) {
        if (operands.isEmpty()) {
            return testValue == currentValue;
        }
        if (currentValue > testValue) {
            return false;
        }
        return canSolveWithConcat(testValue, currentValue + operands.getFirst(), operands.subList(1, operands.size()))
               || canSolveWithConcat(testValue, currentValue * operands.getFirst(), operands.subList(1, operands.size()))
               || canSolveWithConcat(testValue, concat(currentValue, operands.getFirst()), operands.subList(1, operands.size()));
    }

    private static Long concat(Long first, Long second) {
        return Long.parseLong(first.toString() + second.toString());
    }
}

