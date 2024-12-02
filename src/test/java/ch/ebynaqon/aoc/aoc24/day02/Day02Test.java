package ch.ebynaqon.aoc.aoc24.day02;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/2
class Day02Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """);

        // when
        var actual = Day02.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Report(List.of(7, 6, 4, 2, 1)),
                new Report(List.of(1, 2, 7, 8, 9)),
                new Report(List.of(9, 7, 6, 2, 1)),
                new Report(List.of(1, 3, 2, 4, 5)),
                new Report(List.of(8, 6, 4, 4, 1)),
                new Report(List.of(1, 3, 6, 7, 9))
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """);

        // when
        var result = Day02.solvePart1(input);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/2/input
        RawProblemInput input = RawProblemInput.fromResource("/day02.txt");

        // when
        var result = Day02.solvePart1(input);

        // then
        assertThat(result).isEqualTo(534);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """);

        // when
        var result = Day02.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day02.txt");

        // when
        var result = Day02.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}
