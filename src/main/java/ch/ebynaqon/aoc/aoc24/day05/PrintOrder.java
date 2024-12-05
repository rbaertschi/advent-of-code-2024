package ch.ebynaqon.aoc.aoc24.day05;

import java.util.List;

public record PrintOrder(List<Integer> pages) {
    public PrintOrder {
        if (pages.size() % 2 == 0) {
            throw new IllegalArgumentException("pages must be odd");
        }
    }

    public int getMiddlePage() {
        return pages.get(pages.size() / 2);
    }

    public boolean isInCorrectOrder(List<OrderingRule> rules) {
        for (int i = 0; i < pages.size(); i++) {
            Integer currentPage = pages.get(i);
            for (OrderingRule rule : rules) {
                if (rule.pageAfter() == currentPage) {
                    for (int j = i + 1; j < pages.size(); j++) {
                        Integer futurePage = pages.get(j);
                        if (rule.pageBefore() == futurePage) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
