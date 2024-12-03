package ch.ebynaqon.aoc.aoc24.day03;

sealed interface Instruction permits Multiplication, Do, DoNot {
    int evaluate();
}

record Do() implements Instruction {
    @Override
    public int evaluate() {
        return 0;
    }
}

record DoNot() implements Instruction {
    @Override
    public int evaluate() {
        return 0;
    }
}

record Multiplication(int value1, int value2) implements Instruction {
    @Override
    public int evaluate() {
        return value1 * value2;
    }

}
