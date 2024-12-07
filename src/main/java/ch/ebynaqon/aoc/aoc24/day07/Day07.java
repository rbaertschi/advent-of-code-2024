package ch.ebynaqon.aoc.aoc24.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day07 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Equation> samples = input.getLines().stream().map(Day07::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static Equation parseLine(String input) {
        String[] targetValueAndOperands = input.split(":\\s+");
        List<Long> operands = Arrays.stream(targetValueAndOperands[1].split("\\s+")).map(Long::parseLong).toList();
        long testValue = Long.parseLong(targetValueAndOperands[0]);
        return new Equation(testValue, operands);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().filter(Equation::canBeSolved).mapToLong(Equation::testValue).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().filter(Equation::canBeSolvedWithConcat).mapToLong(Equation::testValue).sum();
    }
}

