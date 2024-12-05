package ch.ebynaqon.aoc.aoc24.day05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

record PrintOrder(List<Integer> pages) {
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

    public PrintOrder putInCorrectOrder(List<OrderingRule> rules) {
        var relevantRules = filterRelevantRules(rules);
        HashSet<Integer> pagesToSort = new HashSet<>(pages);
        ArrayList<Integer> sortedPages = new ArrayList<>();
        while (!pagesToSort.isEmpty()) {
            getNextPage(pagesToSort, relevantRules, sortedPages);
        }
        return new PrintOrder(sortedPages);
    }

    private List<OrderingRule> filterRelevantRules(List<OrderingRule> rules) {
        return rules.stream().filter(rule -> pages.contains(rule.pageBefore())
                                             || pages.contains(rule.pageAfter())).toList();
    }

    private static void getNextPage(HashSet<Integer> pagesToSort, List<OrderingRule> relevantRules, ArrayList<Integer> sortedPages) {
        for (Integer pageToCheck : pagesToSort) {
            if (relevantRules.stream().noneMatch(rule ->
                    rule.pageAfter() == pageToCheck && pagesToSort.contains(rule.pageBefore()))) {
                sortedPages.add(pageToCheck);
                pagesToSort.remove(pageToCheck);
                return;
            }
        }
    }
}
