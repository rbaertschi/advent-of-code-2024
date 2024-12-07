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
        return canSolve(testValue, currentValue + operands.getFirst(), operands.subList(1, operands.size()))
               || canSolve(testValue, currentValue * operands.getFirst(), operands.subList(1, operands.size()));
    }
}

