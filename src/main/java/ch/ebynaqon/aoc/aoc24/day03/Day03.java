package ch.ebynaqon.aoc.aoc24.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Day03 {

    static ProblemInput parseProblem(RawProblemInput input) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input.getWholeInput());
        ArrayList<Multiplication> multiplications = new ArrayList<>(matcher.groupCount());
        while (matcher.find()) {
            Multiplication multiplication = new Multiplication(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            multiplications.add(multiplication);
        }
        return new ProblemInput(multiplications);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().mapToInt(Multiplication::evaluate).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().mapToLong(Multiplication::value1).max().orElseThrow();
    }
}
