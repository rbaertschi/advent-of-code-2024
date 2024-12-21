package ch.ebynaqon.aoc.aoc24.day22;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/22
class Day22Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var actual = Day22.parseProblem(input);

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
        var result = Day22.solvePart1(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/22/input
        RawProblemInput input = RawProblemInput.fromResource("/day22.txt");

        // when
        var result = Day22.solvePart1(input);

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
        var result = Day22.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day22.txt");

        // when
        var result = Day22.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

