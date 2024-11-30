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
                42
                """);

        // when
        var actual = Day01.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new ProblemSample(42L)
        )));
    }

    @Test
    @Disabled
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day01.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/1/input
        RawProblemInput input = RawProblemInput.fromResource("/day01.txt");

        // when
        var result = Day01.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
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
