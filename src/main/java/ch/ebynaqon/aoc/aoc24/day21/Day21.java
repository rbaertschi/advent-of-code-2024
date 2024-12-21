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
        MovementKey currentPad1 = PRESS;
        MovementKey currentPad2 = PRESS;
        List<MovementKey> inputsOnThirdDirectionalKeypad = new ArrayList<>();
        for (NumericKey next : code.keys()) {
            for (MovementKey keyOnPad1 : current.pressNext(next)) {
                for (MovementKey keyOnPad2 : currentPad1.pressNext(keyOnPad1)) {
                    for (MovementKey keyOnPad3 : currentPad2.pressNext(keyOnPad2)) {
                        inputsOnThirdDirectionalKeypad.add(keyOnPad3);
                    }
                    currentPad2 = keyOnPad2;
                }
                currentPad1 = keyOnPad1;
            }
            current = next;
        }
        int size = inputsOnThirdDirectionalKeypad.size();
        int numeric = code.numericValue();
        System.out.println(toString(inputsOnThirdDirectionalKeypad));
        return size * numeric;
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

interface Key {
    int row();

    int column();

    default List<MovementKey> repeat(MovementKey movement, int repetitions) {
        ArrayList<MovementKey> result = new ArrayList<>();
        for (int i = 0; i < repetitions; i++) {
            result.add(movement);
        }
        return result;
    }
}

enum NumericKey implements Key {
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

    public List<MovementKey> pressNext(Key target) {
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

enum MovementKey implements Key {
    /* blank */ UP('^', 0, 1), PRESS('A', 0, 2),
    LEFT('<', 1, 0), DOWN('v', 1, 1), RIGHT('>', 1, 2);

    private final char c;
    private final int row;
    private final int column;

    MovementKey(char c, int row, int column) {
        this.c = c;
        this.row = row;
        this.column = column;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public List<MovementKey> pressNext(Key target) {
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
