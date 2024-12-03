package ch.ebynaqon.aoc.aoc24.day03;

public record DoNot() implements Instruction {
    @Override
    public int evaluate() {
        return 0;
    }
}
