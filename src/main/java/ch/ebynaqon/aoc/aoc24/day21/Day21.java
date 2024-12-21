package ch.ebynaqon.aoc.aoc24.day21;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.PRESS;

interface Day21 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Code> codes = input.getLines().stream().map(Day21::parseLine).toList();
        return new ProblemInput(codes);
    }

    private static Code parseLine(String input) {
        ArrayList<NumericKey> keys = new ArrayList<>();
        for (char c : input.toCharArray()) {
            keys.add(NumericKey.from(c));
        }
        return new Code(keys);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.codes().stream().mapToLong(Day21::calculateCodeComplexity).sum();
    }

    private static int calculateCodeComplexity(Code code) {
        NumericKey current = NumericKey.ENTER;
        int numberOfMovements = 0;
        for (NumericKey next : code.keys()) {
            numberOfMovements += getMinNumberOfMovementsOnPad1(current, next);
            current = next;
        }
        return numberOfMovements * code.numericValue();
    }

    private static int getMinNumberOfMovementsOnPad1(NumericKey current, NumericKey next) {
        int minNumberOfMovements = Integer.MAX_VALUE;
        List<MovementKey> movements = current.movementsToNextPress(next);
        for (List<MovementKey> movementVariation : validPermutationsWithPress(current, movements)) {
            int numberOfMovements = 0;
            MovementKey currentPad1 = PRESS;
            for (MovementKey keyOnPad1 : movementVariation) {
                numberOfMovements += getMinNumberOfMovementsOnPad2(currentPad1, keyOnPad1);
                currentPad1 = keyOnPad1;
            }
            if (numberOfMovements < minNumberOfMovements) {
                minNumberOfMovements = numberOfMovements;
            }
        }
        return minNumberOfMovements;
    }

    private static int getMinNumberOfMovementsOnPad2(MovementKey current, MovementKey next) {
        int minNumberOfMovements = Integer.MAX_VALUE;
        List<MovementKey> movements = current.movementsToNextPress(next);
        for (List<MovementKey> movementVariation : validPermutationsWithPress(current, movements)) {
            int numberOfMovements = 0;
            MovementKey currentPad2 = PRESS;
            for (MovementKey keyOnPad2 : movementVariation) {
                numberOfMovements += currentPad2.movementsToNextPress(keyOnPad2).size();
                currentPad2 = keyOnPad2;
            }
            if (numberOfMovements < minNumberOfMovements) {
                minNumberOfMovements = numberOfMovements;
            }
        }
        return minNumberOfMovements;
    }

    static <T extends Key<T>> List<List<MovementKey>> validPermutationsWithPress(T start, List<MovementKey> movementsAndPress) {
        List<MovementKey> onlyMovements = movementsAndPress.subList(0, movementsAndPress.size() - 1);
        List<List<MovementKey>> permutations = validPermutations(start, onlyMovements);
        permutations.forEach(movements -> movements.add(PRESS));
        return permutations;
    }

    private static <T extends Key<T>> List<List<MovementKey>> validPermutations(T start, List<MovementKey> movements) {
        List<List<MovementKey>> permutations = new ArrayList<>();
        if (movements.size() > 1) {
            for (int i = 0; i < movements.size(); i++) {
                MovementKey firstMove = movements.get(i);
                T nextStart = start.move(firstMove);
                if (nextStart != null) {
                    ArrayList<MovementKey> nextMovements = new ArrayList<>(movements.subList(0, i));
                    nextMovements.addAll(movements.subList(i + 1, movements.size()));
                    List<List<MovementKey>> validPermutationsOfRemainingMoves = validPermutations(nextStart, nextMovements);
                    for (List<MovementKey> permutation : validPermutationsOfRemainingMoves) {
                        ArrayList<MovementKey> validPermutation = new ArrayList<>();
                        validPermutation.add(firstMove);
                        validPermutation.addAll(permutation);
                        permutations.add(validPermutation);
                    }
                }
            }
        } else {
            permutations.add(movements);
        }
        return permutations;
    }

    static String toString(List<MovementKey> movements) {
        return movements.stream().map(MovementKey::toString).collect(Collectors.joining());
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}


record ProblemInput(List<Code> codes) {
}

record Code(List<NumericKey> keys) {
    public int numericValue() {
        int result = 0;
        for (NumericKey key : keys) {
            if (key != NumericKey.ENTER) {
                result = result * 10 + key.numericValue();
            }
        }
        return result;
    }
}

interface Key<SELF> {
    int row();

    int column();

    default List<MovementKey> repeat(MovementKey movement, int repetitions) {
        ArrayList<MovementKey> result = new ArrayList<>();
        for (int i = 0; i < repetitions; i++) {
            result.add(movement);
        }
        return result;
    }

    SELF move(MovementKey movement);
}

enum NumericKey implements Key<NumericKey> {
    SEVEN('7', 0, 0), EIGHT('8', 0, 1), NINE('9', 0, 2),
    FOUR('4', 1, 0), FIVE('5', 1, 1), SIX('6', 1, 2),
    ONE('1', 2, 0), TWO('2', 2, 1), THREE('3', 2, 2),
    /* blank */ ZERO('0', 3, 1), ENTER('A', 3, 2);

    private final char key;
    private final int row;
    private final int column;

    NumericKey(char key, int row, int column) {
        this.key = key;
        this.row = row;
        this.column = column;
    }

    public static NumericKey from(char keyChar) {
        return Arrays.stream(NumericKey.values()).filter(it -> it.key == keyChar).findFirst().orElseThrow();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    @Override
    public NumericKey move(MovementKey movement) {
        return Arrays.stream(values())
                .filter(target -> target.row() == row + movement.deltaRow() && target.column() == column + movement.deltaColumn())
                .findFirst()
                .orElse(null);
    }

    public List<MovementKey> movementsToNextPress(NumericKey target) {
        List<MovementKey> movements = new ArrayList<>();
        if (target != this) {
            MovementKey vertical = target.row() < row() ? MovementKey.UP : MovementKey.DOWN;
            MovementKey horizontal = target.column() < column() ? MovementKey.LEFT : MovementKey.RIGHT;
            if (vertical == MovementKey.UP) {
                movements.addAll(repeat(vertical, Math.abs(target.row() - row())));
                movements.addAll(repeat(horizontal, Math.abs(target.column() - column())));
            } else {
                movements.addAll(repeat(horizontal, Math.abs(target.column() - column())));
                movements.addAll(repeat(vertical, Math.abs(target.row() - row())));
            }
        }
        movements.add(PRESS);
        return movements;
    }

    public int numericValue() {
        return Math.max(0, Math.min(9, key - '0'));
    }
}

enum MovementKey implements Key<MovementKey> {
    /* blank */ UP('^', 0, 1, -1, 0), PRESS('A', 0, 2, 0, 0),
    LEFT('<', 1, 0, 0, -1), DOWN('v', 1, 1, 1, 0), RIGHT('>', 1, 2, 0, 1);

    private final char c;
    private final int row;
    private final int column;
    private final int deltaRow;
    private final int deltaColumn;

    MovementKey(char c, int row, int column, int deltaRow, int deltaColumn) {
        this.c = c;
        this.row = row;
        this.column = column;
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public int deltaRow() {
        return deltaRow;
    }

    public int deltaColumn() {
        return deltaColumn;
    }

    @Override
    public MovementKey move(MovementKey movement) {
        return Arrays.stream(values())
                .filter(target -> target.row() == row + movement.deltaRow() && target.column() == column + movement.deltaColumn())
                .findFirst()
                .orElse(null);
    }

    public List<MovementKey> movementsToNextPress(MovementKey target) {
        List<MovementKey> movements = new ArrayList<>();
        if (target != this) {
            MovementKey vertical = target.row() < row() ? UP : DOWN;
            MovementKey horizontal = target.column() < column() ? LEFT : RIGHT;
            if (vertical == DOWN) {
                movements.addAll(repeat(vertical, Math.abs(target.row() - row())));
                movements.addAll(repeat(horizontal, Math.abs(target.column() - column())));
            } else {
                movements.addAll(repeat(horizontal, Math.abs(target.column() - column())));
                movements.addAll(repeat(vertical, Math.abs(target.row() - row())));
            }
        }
        movements.add(PRESS);
        return movements;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }
}
