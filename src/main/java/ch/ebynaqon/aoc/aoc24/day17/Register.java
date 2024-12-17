package ch.ebynaqon.aoc.aoc24.day17;

public record Register(int a, int b, int c, int instructionPointer) {
    public Register withA(int a) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withB(int b) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withC(int c) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withInstructionPointer(int instructionPointer) {
        return new Register(a, b, c, instructionPointer);
    }

    public Register next() {
        return new Register(a, b, c, instructionPointer + 2);
    }
}
