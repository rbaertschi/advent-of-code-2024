package ch.ebynaqon.aoc.aoc24.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day01 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        List<Integer> left = new ArrayList<>(lines.size());
        List<Integer> right = new ArrayList<>(lines.size());
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
        return new ProblemInput(left, right);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Integer> left = problem.left().stream().sorted().toList();
        List<Integer> right = problem.right().stream().sorted().toList();
        long sumOfDistances = 0L;
        for (int i = 0; i < left.size(); i++) {
            sumOfDistances += Math.abs(left.get(i) - right.get(i));
        }
        return sumOfDistances;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Integer> left = problem.left();
        List<Integer> right = problem.right();
        long result = 0L;
        for (Integer value : left) {
            long occurrencesInRightList = right.stream().filter(value::equals).count();
            result += value * occurrencesInRightList;
        }
        return result;
    }
}
