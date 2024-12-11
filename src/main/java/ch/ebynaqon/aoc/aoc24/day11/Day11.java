package ch.ebynaqon.aoc.aoc24.day11;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.HashMap;
import java.util.Map;

interface Day11 {

    static Map<Long, Long> parseProblem(RawProblemInput input) {
        Map<Long, Long> stoneCounts = new HashMap<>();
        for (String stone : input.getWholeInput().split("\\s+")) {
            long stoneValue = Long.parseLong(stone);
            stoneCounts.put(stoneValue, stoneCounts.getOrDefault(stoneValue, 0L) + 1);
        }
        return stoneCounts;
    }

    static long solvePart1(RawProblemInput input) {
        Map<Long, Long> stoneCounts = parseProblem(input);
        for (int i = 0; i < 25; i++) {
            stoneCounts = evolve(stoneCounts);
        }
        return stoneCounts.values().stream().mapToInt(Long::intValue).sum();
    }

    static Map<Long, Long> evolve(Map<Long, Long> stoneCounts) {
        HashMap<Long, Long> nextStoneCount = new HashMap<>();
        for (Map.Entry<Long, Long> entry : stoneCounts.entrySet()) {
            long stoneValue = entry.getKey();
            long stoneCount = entry.getValue();
            for (long newStoneValue : evolve(stoneValue)) {
                nextStoneCount.put(newStoneValue, nextStoneCount.getOrDefault(newStoneValue, 0L) + stoneCount);
            }
        }
        return nextStoneCount;
    }

    static long[] evolve(long stoneValue) {
        /*If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.*/
        if (stoneValue == 0) {
            return new long[]{1};
        }
        long numberOfDigits = numberOfDigits(stoneValue);
        if (numberOfDigits % 2 == 0) {
            long leftNumber = stoneValue;
            long multiplier = 1;
            for (long i = 0; i < numberOfDigits / 2; i++) {
                leftNumber /= 10;
                multiplier *= 10;
            }
            return new long[]{leftNumber, stoneValue - leftNumber * multiplier};
        }
        return new long[]{stoneValue * 2024};
    }

    static long numberOfDigits(long value) {
        long result = 0;
        while (value > 0) {
            value /= 10;
            result++;
        }
        return result;
    }

    static long solvePart2(RawProblemInput input) {
        Map<Long, Long> problem = parseProblem(input);
        return 0;
    }
}

