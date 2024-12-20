package ch.ebynaqon.aoc.aoc24.day20;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/20
class Day20Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #####
                ###E#
                #S# #
                # # #
                #   #
                #####
                """);

        // when
        var actual = Day20.parseProblem(input);

        // then
        assertThat(actual.obstacles()).isEqualTo(new boolean[][]{
                {true, true, true, true, true},
                {true, true, true, false, true},
                {true, false, true, false, true},
                {true, false, true, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        });
        assertThat(actual.start()).isEqualTo(new Position(2, 1));
        assertThat(actual.end()).isEqualTo(new Position(1, 3));
    }

    @ParameterizedTest
    @MethodSource("cheatsFor2Picoseconds")
    void solvePart1UsingExample(int atLeastTimeSaved, int numberOfCheats) {
        // given
        RawProblemInput input = new RawProblemInput("""
                ###############
                #...#...#.....#
                #.#.#.#.#.###.#
                #S#...#.#.#...#
                #######.#.#.###
                #######.#.#...#
                #######.#.###.#
                ###..E#...#...#
                ###.#######.###
                #...###...#...#
                #.#####.#.###.#
                #.#...#.#.#...#
                #.#.#.#.#.#.###
                #...#...#...###
                ###############
                """);

        // when
        var result = Day20.solvePart1(input, atLeastTimeSaved);

        // then
        assertThat(result).isEqualTo(numberOfCheats);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/20/input
        RawProblemInput input = RawProblemInput.fromResource("/day20.txt");

        // when
        var result = Day20.solvePart1(input, 100);

        // then
        assertThat(result).isEqualTo(1409);
    }

    @ParameterizedTest
    @MethodSource("cheatsFor20Picoseconds")
    void solvePart2UsingExample(int atLeastTimeSaved, int numberOfCheats) {
        // given
        RawProblemInput input = new RawProblemInput("""
                ###############
                #...#...#.....#
                #.#.#.#.#.###.#
                #S#...#.#.#...#
                #######.#.#.###
                #######.#.#...#
                #######.#.###.#
                ###..E#...#...#
                ###.#######.###
                #...###...#...#
                #.#####.#.###.#
                #.#...#.#.#...#
                #.#.#.#.#.#.###
                #...#...#...###
                ###############
                """);

        // when
        var result = Day20.solvePart2(input, atLeastTimeSaved);

        // then
        assertThat(result).isEqualTo(numberOfCheats);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day20.txt");

        // when
        var result = Day20.solvePart2(input, 100);

        // then
        assertThat(result).isEqualTo(1012821);
    }

    static Stream<Arguments> cheatsFor2Picoseconds() {
        return Stream.of(
                Arguments.arguments(2, 44),
                Arguments.arguments(4, 30),
                Arguments.arguments(6, 16),
                Arguments.arguments(8, 14),
                Arguments.arguments(10, 10),
                Arguments.arguments(12, 8),
                Arguments.arguments(20, 5),
                Arguments.arguments(36, 4),
                Arguments.arguments(38, 3),
                Arguments.arguments(40, 2),
                Arguments.arguments(64, 1)
        );
    }

    static Stream<Arguments> cheatsFor20Picoseconds() {
        return Stream.of(
                Arguments.arguments(50, 285),
                Arguments.arguments(52, 253),
                Arguments.arguments(54, 222),
                Arguments.arguments(56, 193),
                Arguments.arguments(58, 154),
                Arguments.arguments(60, 129),
                Arguments.arguments(62, 106),
                Arguments.arguments(64, 86),
                Arguments.arguments(66, 67),
                Arguments.arguments(68, 55),
                Arguments.arguments(70, 41),
                Arguments.arguments(72, 29),
                Arguments.arguments(74, 7),
                Arguments.arguments(76, 3)
        );
    }

}

