package ch.ebynaqon.aoc.aoc24.day03;

record Multiplication(int value1, int value2) {
    public int evaluate() {
        return value1 * value2;
    }
}
