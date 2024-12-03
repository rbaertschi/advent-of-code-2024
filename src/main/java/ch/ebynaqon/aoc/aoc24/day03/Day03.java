package ch.ebynaqon.aoc.aoc24.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Day03 {

    static ProblemInput parseProblem(RawProblemInput input) {
        Pattern pattern = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\))");
        Matcher matcher = pattern.matcher(input.getWholeInput());
        ArrayList<Instruction> instructions = new ArrayList<>(matcher.groupCount());
        while (matcher.find()) {
            if (matcher.group(1).startsWith("mul")) {
                Multiplication multiplication = new Multiplication(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
                instructions.add(multiplication);
            } else if (matcher.group(1).equals("don't()")) {
                instructions.add(new DoNot());
            } else if (matcher.group(1).equals("do()")) {
                instructions.add(new Do());
            }
        }
        return new ProblemInput(instructions);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.instructions().stream().mapToInt(Instruction::evaluate).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        long result = 0;
        boolean enabled = true;
        for (Instruction instruction : problem.instructions()) {
            switch (instruction) {
                case Do() -> enabled = true;
                case DoNot() -> enabled = false;
                case Multiplication mul -> result += enabled ? mul.evaluate() : 0;
            }
        }
        return result;
    }
}
