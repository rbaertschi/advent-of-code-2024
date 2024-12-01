package ch.ebynaqon.aoc.aoc24.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import ch.ebynaqon.aoc.helper.Statistics;

import java.util.ArrayList;
import java.util.List;

interface Day01 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int numberOfEntries = lines.size();
        List<Integer> left = new ArrayList<>(numberOfEntries);
        List<Integer> right = new ArrayList<>(numberOfEntries);
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
        return new ProblemInput(left, right);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Integer> leftSorted = problem.left().stream().sorted().toList();
        List<Integer> rightSorted = problem.right().stream().sorted().toList();
        long sumOfDistances = 0L;
        for (int i = 0; i < leftSorted.size(); i++) {
            sumOfDistances += Math.abs(leftSorted.get(i) - rightSorted.get(i));
        }
        return sumOfDistances;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        var rightStatistics = Statistics.entryCount(problem.right());
        return problem.left().stream()
                .mapToLong(value -> value * rightStatistics.getOrDefault(value, 0L))
                .sum();
    }

}
