package ch.ebynaqon.aoc.aoc24.day17;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Day17 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] registersAndProgram = input.getWholeInput().split("\\n\\n");
        return new ProblemInput(
                parseRegisters(registersAndProgram[0]),
                parseProgram(registersAndProgram[1])
        );
    }

    private static List<Short> parseProgram(String input) {
        return Arrays.stream(input.split(":")[1].trim().split(",")).map(Short::parseShort).toList();
    }

    static Register parseRegisters(String input) {
        String[] registers = input.split("\\n");
        return new Register(
                parseRegister(registers[0]),
                parseRegister(registers[1]),
                parseRegister(registers[2]),
                0
        );
    }

    static long parseRegister(String register) {
        return Long.parseLong(register.split(":")[1].trim());
    }

    static String solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        OutputDevice outputDevice = new OutputDevice();
        Register register = problem.register();
        while (register.instructionPointer() < problem.program().size()) {
            Short opCode = problem.program().get(register.instructionPointer());
            Short operand = problem.program().get(register.instructionPointer() + 1);
            register = Instruction.from(opCode, operand).process(register, outputDevice);
        }
        return outputDevice.output();
    }

    static long solvePart2BruteForce(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Short> program = problem.program();
        String target = program.stream().map(String::valueOf).collect(Collectors.joining(","));
        long result = 0L;
        while (true) {
            OutputDevice outputDevice = new OutputDevice();
            Register register = new Register(result, problem.register().b(), problem.register().c(), 0);
            while (register.instructionPointer() < program.size()) {
                Short opCode = program.get(register.instructionPointer());
                Short operand = program.get(register.instructionPointer() + 1);
                register = Instruction.from(opCode, operand).process(register, outputDevice);
                if (outputDevice.output().equals(target)) {
                    return result;
                }
            }
            result++;
        }
    }

    /// The program "2,4,1,5,7,5,4,3,1,6,0,3,5,5,3,0" consists of the following Steps
    /// ```
    /// BST[comboOperand=4]
    /// BXL[literalOperand=5]
    /// CDV[comboOperand=5]
    /// BXC[ignoredOperand=3]
    /// BXL[literalOperand=6]
    /// ADV[comboOperand=3]
    /// OUT[comboOperand=5]
    /// JNZ[literalOperand=0]
    ///```
    ///
    /// or written as individual statements
    ///
    /// ```
    /// B = A % 8
    /// B = B ^ 5
    /// C = A / ( 1 << B)
    /// B = B ^ C
    /// B = B ^ 6
    /// A = A / ( 1 << 3)
    /// print(B % 8)
    /// if (a != 0) goto 0
    ///```
    ///
    /// and in java code that basically corresponds to
    ///
    /// ```
    /// int A = 0; // initial A register value
    /// int B = 0; // initial B register value
    /// do {
    ///     B = (((A % 8) ^ 5) ^ (A / ( 1 << ((A % 8) ^ 5)))) ^ 6;
    ///     A = A / 8;
    ///     print(B % 8);
    ///} while (A != 0);
    ///```
    ///
    /// So we know that in order for the program to halt we need to have value 0 in register A.
    /// And we know that in the previous iteration the value of A was 8 times more plus a modulo between 0 and 7,
    /// which is lost in the division.
    /// We also know the expected outputs, so we can reverse engineer the input one iteration after the other.
    static long solvePart2Analytically(RawProblemInput input) {
        return findRegisterAValueYieldingProgramInOutput(0, parseProblem(input).program());
    }

    private static long findRegisterAValueYieldingProgramInOutput(long currentA, List<Short> target) {
        if (target.isEmpty()) {
            return currentA;
        }
        for (long nextA : possibleValues(currentA * 8)) {
            if (printValueFor(nextA) == target.getLast()) {
                long next = findRegisterAValueYieldingProgramInOutput(nextA, target.subList(0, target.size() - 1));
                if (next > -1) {
                    return next;
                }
            }
        }
        return -1;
    }

    static short printValueFor(long a) {
        return (short) (((((a % 8) ^ 5) ^ (a / (1 << ((a % 8) ^ 5)))) ^ 6) % 8);
    }

    static long[] possibleValues(long i) {
        return new long[]{i, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7};
    }
}
