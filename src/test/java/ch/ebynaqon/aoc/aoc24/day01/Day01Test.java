package ch.ebynaqon.aoc.aoc24.day01;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/1
public class Day01Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """);

        // when
        var actual = Day01.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Pair(3L, 4L),
                new Pair(4L, 3L),
                new Pair(2L, 5L),
                new Pair(1L, 3L),
                new Pair(3L, 9L),
                new Pair(3L, 3L)
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """);

        // when
        var result = Day01.solvePart1(input);

        // then
        assertThat(result).isEqualTo(11);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/1/input
        RawProblemInput input = RawProblemInput.fromResource("/day01.txt");

        // when
        var result = Day01.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1873376);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """);

        // when
        var result = Day01.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day01.txt");

        // when
        var result = Day01.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}
