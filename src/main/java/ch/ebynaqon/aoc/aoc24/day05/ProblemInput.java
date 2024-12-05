package ch.ebynaqon.aoc.aoc24.day05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record ProblemInput(List<OrderingRule> rules, List<PrintOrder> printOrders) {
    public Map<Integer, List<Integer>> rulesMap() {
        HashMap<Integer, List<Integer>> result = new HashMap<>();
        for (OrderingRule rule : rules) {
            if (!result.containsKey(rule.pageBefore())) {
                result.put(rule.pageBefore(), new ArrayList<>());
            }
            result.get(rule.pageBefore()).add(rule.pageAfter());
        }
        for (OrderingRule rule : rules) {
            var dependents = result.get(rule.pageBefore());
            Integer nextToCheck = dependents.getFirst();
            while (nextToCheck != null) {
                List<Integer> next = result.get(nextToCheck);
                if (next == null) {
                    nextToCheck = null;
                    continue;
                }
                nextToCheck = next.getFirst();
                dependents.add(nextToCheck);
            }
        }
        return result;
    }
}
