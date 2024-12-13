package ch.ebynaqon.aoc.aoc24.day13;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/13
class Day13Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                """);

        // when
        var actual = Day13.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Game(
                        new Vector(94, 34),
                        new Vector(22, 67),
                        new Vector(8400, 5400)
                ),
                new Game(
                        new Vector(26, 66),
                        new Vector(67, 21),
                        new Vector(12748, 12176)
                )
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """);

        // when
        var result = Day13.solvePart1(input);

        // then
        assertThat(result).isEqualTo(480);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/13/input
        RawProblemInput input = RawProblemInput.fromResource("/day13.txt");

        // when
        var result = Day13.solvePart1(input);

        // then
        assertThat(result).isEqualTo(31897);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day13.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day13.txt");

        // when
        var result = Day13.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

