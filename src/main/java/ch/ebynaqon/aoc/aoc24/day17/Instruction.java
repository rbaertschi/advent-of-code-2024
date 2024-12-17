package ch.ebynaqon.aoc.aoc24.day17;

sealed interface Instruction {
    static Instruction from(short opCode, short operand) {
        return switch (opCode) {
            case 0 -> new ADV(operand);
            case 1 -> new BXL(operand);
            case 2 -> new BST(operand);
            case 3 -> new JNZ(operand);
            case 4 -> new BXC(operand);
            case 5 -> new OUT(operand);
            case 6 -> new BDV(operand);
            case 7 -> new CDV(operand);
            default -> throw new IllegalStateException("Unexpected value: " + opCode);
        };
    }

    Register process(Register register, OutputDevice output);

    /*
    Combo operands 0 through 3 represent literal values 0 through 3.
    Combo operand 4 represents the value of register A.
    Combo operand 5 represents the value of register B.
    Combo operand 6 represents the value of register C.
    Combo operand 7 is reserved and will not appear in valid programs.
    */
    default int combo(short comboOperand, Register register) {
        return switch (comboOperand) {
            case 0, 1, 2, 3 -> comboOperand;
            case 4 -> register.a();
            case 5 -> register.b();
            case 6 -> register.c();
            default -> throw new IllegalStateException("Unexpected combo operand: " + comboOperand);
        };
    }
}

/*
    The adv instruction (opcode 0) performs division. The numerator is the value in the A register.
    The denominator is found by raising 2 to the power of the instruction's combo operand.
    (So, an operand of 2 would divide A by 4 (2^2); an operand of 5 would divide A by 2^B.)
    The result of the division operation is truncated to an integer and then written to the A register.
*/
record ADV(short comboOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withA(register.a() / (1 << combo(comboOperand, register)));
    }
}


/*
    The bxl instruction (opcode 1) calculates the bitwise XOR of register B and the instruction's literal operand,
     then stores the result in register B.
*/
record BXL(short literalOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withB(register.b() ^ literalOperand);
    }
}

/*
    The bst instruction (opcode 2) calculates the value of its combo operand modulo 8 (thereby keeping only its lowest 3
     bits), then writes that value to the B register.
*/
record BST(short comboOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withB(combo(comboOperand, register) % 8);
    }
}

/*
    The jnz instruction (opcode 3) does nothing if the A register is 0. However, if the A register is not zero, it jumps by
    setting the instruction pointer to the value of its literal operand; if this instruction jumps, the instruction pointer
    is not increased by 2 after this instruction.
*/
record JNZ(short literalOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withInstructionPointer(register.a() == 0 ? register.instructionPointer() + 2 : literalOperand);
    }
}

/*
    The bxc instruction (opcode 4) calculates the bitwise XOR of register B and register C, then stores the result in
    register B. (For legacy reasons, this instruction reads an operand but ignores it.)
*/
record BXC(int ignoredOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withB(register.b() ^ register.c());
    }
}

/*
    The out instruction (opcode 5) calculates the value of its combo operand modulo 8, then outputs that value. (If a program
    outputs multiple values, they are separated by commas.)
*/
record OUT(short comboOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        output.write(combo(comboOperand, register) % 8);
        return register.next();
    }
}

/*
    The bdv instruction (opcode 6) works exactly like the adv instruction except that the result is stored in the B
    register. (The numerator is still read from the A register.)
*/
record BDV(short comboOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withB(register.a() / (1 << combo(comboOperand, register)));
    }
}

/*
    The cdv instruction (opcode 7) works exactly like the adv instruction except that the result is stored in the C register.
    (The numerator is still read from the A register.)
*/
record CDV(short comboOperand) implements Instruction {
    @Override
    public Register process(Register register, OutputDevice output) {
        return register.withC(register.a() / (1 << combo(comboOperand, register)));
    }
}
