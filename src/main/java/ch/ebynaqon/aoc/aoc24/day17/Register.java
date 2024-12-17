package ch.ebynaqon.aoc.aoc24.day17;

record Register(long a, long b, long c, int instructionPointer) {
    public Register withA(long a) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withB(long b) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withC(long c) {
        return new Register(a, b, c, instructionPointer + 2);
    }

    public Register withInstructionPointer(int instructionPointer) {
        return new Register(a, b, c, instructionPointer);
    }

    public Register next() {
        return new Register(a, b, c, instructionPointer + 2);
    }
}
