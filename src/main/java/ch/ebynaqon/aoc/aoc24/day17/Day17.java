package ch.ebynaqon.aoc.aoc24.day17;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    static int parseRegister(String register) {
        return Integer.parseInt(register.split(":")[1].trim());
    }

    static String solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        OutputDevice outputDevice = new OutputDevice();
        Register register = problem.register();
        while(register.instructionPointer() < problem.program().size()) {
            Short opCode = problem.program().get(register.instructionPointer());
            Short operand = problem.program().get(register.instructionPointer() + 1);
            register = Instruction.from(opCode, operand).process(register, outputDevice);
        }
        return outputDevice.output();
    }

    static String solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return null;
    }
}

