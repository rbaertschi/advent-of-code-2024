package ch.ebynaqon.aoc.aoc24.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;

interface Day02 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Report> samples = input.getLines().stream().map(Day02::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static Report parseLine(String input) {
        return new Report(Arrays.stream(input.split("\\s+")).map(Integer::parseInt).toList());
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.reports().stream().filter(Report::isSafe).count();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.reports().stream().filter(Report::isSafe).count();
    }
}
