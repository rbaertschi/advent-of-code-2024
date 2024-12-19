package ch.ebynaqon.aoc.aoc24.day19;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        return problem.desiredPatterns().stream()
                .filter(desiredPattern -> isPossible(desiredPattern, new HashSet<>(problem.availablePatterns()))).count();
    }

    static boolean isPossible(String desiredPattern, Set<String> availablePatterns) {
        if (desiredPattern.isEmpty()) {
            return true;
        }
        for (int i = 1; i <= desiredPattern.length(); i++) {
            String currentPattern = desiredPattern.substring(0, i);
            if (availablePatterns.contains(currentPattern)) {
                boolean isRestPossible = isPossible(desiredPattern.substring(i), availablePatterns);
                if (isRestPossible) {
                    return true;
                }
            }
        }
        return false;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

record ProblemInput(List<String> availablePatterns, List<String> desiredPatterns) {
}

