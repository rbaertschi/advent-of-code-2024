package ch.ebynaqon.aoc.aoc24.day03;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/3
class Day03Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                """);

        // when
        var actual = Day03.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Multiplication(2,4),
                new Multiplication(5,5),
                new Multiplication(11,8),
                new Multiplication(8,5)
        )));
    }

    @Test
    void parseProblemInputPart2() {
        // given
        RawProblemInput input = new RawProblemInput("""
                xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
                """);

        // when
        var actual = Day03.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Multiplication(2,4),
                new DoNot(),
                new Multiplication(5,5),
                new Multiplication(11,8),
                new Do(),
                new Multiplication(8,5)
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                """);

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(161);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/3/input
        RawProblemInput input = RawProblemInput.fromResource("/day03.txt");

        // when
        var result = Day03.solvePart1(input);

        // then
        assertThat(result).isEqualTo(157621318);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
                """);

        // when
        var result = Day03.solvePart2(input);

        // then
        assertThat(result).isEqualTo(48);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day03.txt");

        // when
        var result = Day03.solvePart2(input);

        // then
        assertThat(result).isEqualTo(79845780);
    }

}
