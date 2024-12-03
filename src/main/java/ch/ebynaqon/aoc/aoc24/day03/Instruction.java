package ch.ebynaqon.aoc.aoc24.day03;

public sealed interface Instruction permits Multiplication, Do, DoNot {
    int evaluate();
}
