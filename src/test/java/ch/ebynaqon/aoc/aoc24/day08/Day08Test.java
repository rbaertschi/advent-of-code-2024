package ch.ebynaqon.aoc.aoc24.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/8
class Day08Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """);

        // when
        var actual = Day08.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(Map.of(
                '0', List.of(new Position(1, 8), new Position(2, 5), new Position(3, 7), new Position(4, 4)),
                'A', List.of(new Position(5, 6),new Position(8, 8), new Position(9, 9))
        ), 12, 12));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """);

        // when
        var result = Day08.solvePart1(input);

        // then
        assertThat(result).isEqualTo(14);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/8/input
        RawProblemInput input = RawProblemInput.fromResource("/day08.txt");

        // when
        var result = Day08.solvePart1(input);

        // then
        assertThat(result).isEqualTo(336);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """);

        // when
        var result = Day08.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day08.txt");

        // when
        var result = Day08.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

