package ch.ebynaqon.aoc.helper;

import java.util.HashMap;

public interface Statistics {
    static <T> HashMap<T, Long> entryCount(Iterable<T> values) {
        var rightStatistics = new HashMap<T, Long>();
        for (T value : values) {
            rightStatistics.put(value, rightStatistics.getOrDefault(value, 0L) + 1L);
        }
        return rightStatistics;
    }
}
