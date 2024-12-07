package ch.ebynaqon.aoc.aoc24.day07;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/7
class Day07Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """);

        // when
        var actual = Day07.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Equation(190L, List.of(10L, 19L)),
                new Equation(3267L, List.of(81L, 40L, 27L)),
                new Equation(83L, List.of(17L, 5L)),
                new Equation(156L, List.of(15L, 6L)),
                new Equation(7290L, List.of(6L, 8L, 6L, 15L)),
                new Equation(161011L, List.of(16L, 10L, 13L)),
                new Equation(192L, List.of(17L, 8L, 14L)),
                new Equation(21037L, List.of(9L, 7L, 18L, 13L)),
                new Equation(292L, List.of(11L, 6L, 16L, 20L))
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """);

        // when
        var result = Day07.solvePart1(input);

        // then
        assertThat(result).isEqualTo(3749L);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/7/input
        RawProblemInput input = RawProblemInput.fromResource("/day07.txt");

        // when
        var result = Day07.solvePart1(input);

        // then
        assertThat(result).isEqualTo(975671981569L);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """);

        // when
        var result = Day07.solvePart2(input);

        // then
        assertThat(result).isEqualTo(11387L);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day07.txt");

        // when
        var result = Day07.solvePart2(input);

        // then
        assertThat(result).isEqualTo(223472064194845L);
    }

}

