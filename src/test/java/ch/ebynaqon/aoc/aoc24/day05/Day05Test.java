package ch.ebynaqon.aoc.aoc24.day05;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/5
class Day05Test {
    @Nested
    class ProblemInputTest {
        @Test
        void rulesMap() {
            ProblemInput input = new ProblemInput(List.of(
                    new OrderingRule(1, 2),
                    new OrderingRule(3, 4),
                    new OrderingRule(2, 3)
            ), List.of());

            assertThat(input.rulesMap()).isEqualTo(Map.of(
                    1, List.of(2,3,4),
                    2, List.of(3,4),
                    3, List.of(4)
            ));
        }
    }

    @Nested
    class PrintOrderTest {
        @Test
        void middlePageWith5PagesIsThirdPage() {
            assertThat(new PrintOrder(List.of(1, 2, 3, 4, 5)).getMiddlePage()).isEqualTo(3);
        }

        @ParameterizedTest
        @MethodSource("rulesAndValidity")
        void order123(List<OrderingRule> rules, boolean isValid) {
            PrintOrder order = new PrintOrder(List.of(1, 2, 3));

            assertThat(order.isInCorrectOrder(rules)).isEqualTo(isValid);
        }

        public static Stream<Arguments> rulesAndValidity() {
            return Stream.of(
                    Arguments.arguments(List.of(), true),
                    Arguments.arguments(List.of(new OrderingRule(1,2)), true),
                    Arguments.arguments(List.of(new OrderingRule(1,3)), true),
                    Arguments.arguments(List.of(new OrderingRule(2,3)), true),
                    Arguments.arguments(List.of(new OrderingRule(3,1)), false),
                    Arguments.arguments(List.of(new OrderingRule(2,1)), false),
                    Arguments.arguments(List.of(new OrderingRule(3,2)), false)
            );
        }

    }

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                47|53
                97|13
                97|61
                
                75,47,61,53,29
                75,29,13
                """);

        // when
        var actual = Day05.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new OrderingRule(47, 53),
                new OrderingRule(97, 13),
                new OrderingRule(97, 61)
        ), List.of(
                new PrintOrder(List.of(75, 47, 61, 53, 29)),
                new PrintOrder(List.of(75, 29, 13))
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13
                
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47
                """);

        // when
        var result = Day05.solvePart1(input);

        // then
        assertThat(result).isEqualTo(143);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/5/input
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart1(input);

        // then
        assertThat(result).isEqualTo(5108);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13
                
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47
                """);

        // when
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(123);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day05.txt");

        // when
        var result = Day05.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}
