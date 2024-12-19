package ch.ebynaqon.aoc.aoc24.day19;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

interface Day19 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] availableAndDesiredPatterns = input.getWholeInput().split("\\n\\n");
        return new ProblemInput(
                Arrays.asList(availableAndDesiredPatterns[0].split(",\\s*")),
                Arrays.asList(availableAndDesiredPatterns[1].split("\\n"))
        );
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        Map<String, Long> cache = new HashMap<>();
        return problem.desiredPatterns().stream()
                .filter(desiredPattern -> possibleCombinations(desiredPattern,
                        new HashSet<>(problem.availablePatterns()), cache) > 0).count();
    }

    static long possibleCombinations(String desiredPattern, Set<String> availablePatterns, Map<String, Long> cache) {
        if (desiredPattern.isEmpty()) {
            return 1;
        }
        if (cache.containsKey(desiredPattern)) {
            return cache.get(desiredPattern);
        }
        long possibleCombinations = 0;
        for (int i = 1; i <= desiredPattern.length(); i++) {
            String currentPattern = desiredPattern.substring(0, i);
            if (availablePatterns.contains(currentPattern)) {
                possibleCombinations += possibleCombinations(desiredPattern.substring(i), availablePatterns, cache);
            }
        }
        cache.put(desiredPattern, possibleCombinations);
        return possibleCombinations;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        Map<String, Long> cache = new HashMap<>();
        return problem.desiredPatterns().stream()
                .mapToLong(desiredPattern -> possibleCombinations(desiredPattern,
                        new HashSet<>(problem.availablePatterns()), cache)).sum();
    }
}

record ProblemInput(List<String> availablePatterns, List<String> desiredPatterns) {
}

