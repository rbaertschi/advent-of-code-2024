package ch.ebynaqon.aoc.aoc24.day05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

    public PrintOrder putInCorrectOrder(Map<Integer, List<Integer>> rulesMap) {
        ArrayList<Integer> pagesInCorrectOrder = new ArrayList<>(pages.size());
        HashSet<Integer> pagesAlreadyUsed = new HashSet<>();
        for (int posToFill = 0; posToFill < pages.size(); posToFill++) {
            var next = findNextPage(pagesAlreadyUsed, rulesMap);
            pagesInCorrectOrder.add(next);
            pagesAlreadyUsed.add(next);
        }
        return new PrintOrder(pagesInCorrectOrder);
    }

    private Integer findNextPage(HashSet<Integer> pagesAlreadyUsed, Map<Integer, List<Integer>> rulesMap) {
        for (int posToCheck = 0; posToCheck < pages.size(); posToCheck++) {
            if (isNext(posToCheck, pagesAlreadyUsed, rulesMap)) {
                return pages.get(posToCheck);
            }
        }
        throw new IllegalStateException("Should not be reached");
    }

    private boolean isNext(int posToCheck, HashSet<Integer> pagesAlreadyUsed, Map<Integer, List<Integer>> rulesMap) {
        var pageToCheck = pages.get(posToCheck);
        if (pagesAlreadyUsed.contains(pageToCheck)) {
            return false;
        }

        for (int posToCheckAgainst = 0; posToCheckAgainst < pages.size(); posToCheckAgainst++) {
            var pageToCheckAgainst = pages.get(posToCheckAgainst);
            if (posToCheck != posToCheckAgainst && !pagesAlreadyUsed.contains(pageToCheckAgainst)) {
                if (rulesMap.containsKey(pageToCheckAgainst) && rulesMap.get(pageToCheckAgainst).contains(pageToCheck)) {
                    return false;
                }
            }
        }
        return true;
    }
}
