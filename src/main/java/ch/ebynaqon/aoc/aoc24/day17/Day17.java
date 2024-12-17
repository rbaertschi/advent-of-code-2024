package ch.ebynaqon.aoc.aoc24.day17;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Short> program = problem.program();
        List<Integer> target = program.stream().map(Integer::valueOf).toList();
        long result = 0L;
        HashMap<CacheKey, Boolean> cache = new HashMap<>();
        while (true) {
            OutputDevice outputDevice = new OutputDevice();
            Register register = new Register(result, problem.register().b(), problem.register().c(), 0);
            int lastOutputLength = 0;
            List<Integer> remaining = new ArrayList<>(target);
            while (register.instructionPointer() < program.size()) {
                if (cache.getOrDefault(new CacheKey(register, remaining), false)) {
                    break;
                }
                Short opCode = program.get(register.instructionPointer());
                Short operand = program.get(register.instructionPointer() + 1);
                register = Instruction.from(opCode, operand).process(register, outputDevice);
                int outputLength = outputDevice.outputs().size();
                if (outputLength > lastOutputLength) {
                    remaining = remaining.subList(outputLength - lastOutputLength, remaining.size());
                    lastOutputLength = outputLength;
                }
                if (!target.subList(0, outputDevice.outputs().size()).equals(outputDevice.outputs())) {
                    cache.put(new CacheKey(register, remaining), false);
                    break;
                }
                if (remaining.isEmpty()) {
                    return result;
                }
            }
            result++;
        }
    }
}

record CacheKey(Register register, List<Integer> remainingOutput) {
}
