package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.List;

interface Day06 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<ProblemSample> samples = input.getLines().stream().map(Day06::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static ProblemSample parseLine(String input) {
        return new ProblemSample(Long.parseLong(input));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().mapToLong(ProblemSample::value).min().orElseThrow();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().mapToLong(ProblemSample::value).max().orElseThrow();
    }
}

