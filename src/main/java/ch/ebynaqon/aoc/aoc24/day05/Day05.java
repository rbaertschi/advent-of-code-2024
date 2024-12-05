package ch.ebynaqon.aoc.aoc24.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;

interface Day05 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] rulesAndOrders = input.getWholeInput().split("\n\n");
        var rules = Arrays.stream(rulesAndOrders[0].split("\n")).map(Day05::parseRule).toList();
        var orders = Arrays.stream(rulesAndOrders[1].split("\n")).map(Day05::parsePrintOrder).toList();
        return new ProblemInput(rules, orders);
    }

    private static OrderingRule parseRule(String input) {
        String[] parts = input.split("\\|");
        return new OrderingRule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private static PrintOrder parsePrintOrder(String input) {
        return new PrintOrder(Arrays.stream(input.split(",")).map(Integer::parseInt).toList());
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);

        return problem.printOrders().stream()
                .filter(printOrder -> printOrder.isInCorrectOrder(problem.rules()))
                .mapToInt(PrintOrder::getMiddlePage)
                .sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.printOrders().stream()
                .filter(printOrder -> !printOrder.isInCorrectOrder(problem.rules()))
                .map(printOrder -> printOrder.putInCorrectOrder(problem.rulesMap()))
                .mapToInt(PrintOrder::getMiddlePage)
                .sum();
    }
}
