package ch.ebynaqon.aoc.aoc24.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day01 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Pair> samples = input.getLines().stream().map(Day01::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static Pair parseLine(String input) {
        String[] parts = input.split("\\s+");
        return new Pair(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Long> list1 = new ArrayList<>(problem.samples().size());
        List<Long> list2 = new ArrayList<>(problem.samples().size());
        for (Pair pair : problem.samples()) {
            list1.add(pair.left());
            list2.add(pair.right());
        }
        list1 = list1.stream().sorted().toList();
        list2 = list2.stream().sorted().toList();
        long sumOfDistances = 0L;
        for (int i = 0; i < list1.size(); i++) {
            sumOfDistances += Math.abs(list1.get(i) - list2.get(i));
        }
        return sumOfDistances;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.samples().stream().mapToLong(Pair::left).max().orElseThrow();
    }
}
