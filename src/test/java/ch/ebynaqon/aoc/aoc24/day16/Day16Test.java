package ch.ebynaqon.aoc.aoc24.day16;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/16
class Day16Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #########
                #......E#
                ###.###.#
                #.....#.#
                #.###.#.#
                #S..#...#
                #########
                """);

        // when
        var actual = Day16.parseProblem(input);

        // then
        assertThat(actual.start()).isEqualTo(new Position(5, 1));
        assertThat(actual.end()).isEqualTo(new Position(1, 7));
        assertThat(actual.obstacles()).isEqualTo(new boolean[][]{
                {true, true, true, true, true, true, true, true, true},
                {true, false, false, false, false, false, false, false, true},
                {true, true, true, false, true, true, true, false, true},
                {true, false, false, false, false, false, true, false, true},
                {true, false, true, true, true, false, true, false, true},
                {true, false, false, false, true, false, false, false, true},
                {true, true, true, true, true, true, true, true, true}
        });
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ###############
                #.......#....E#
                #.#.###.#.###.#
                #.....#.#...#.#
                #.###.#####.#.#
                #.#.#.......#.#
                #.#.#####.###.#
                #...........#.#
                ###.#.#####.#.#
                #...#.....#.#.#
                #.#.#.###.#.#.#
                #.....#...#.#.#
                #.###.#.#.#.#.#
                #S..#.....#...#
                ###############
                """);

        // when
        var result = Day16.solvePart1(input);

        // then
        assertThat(result).isEqualTo(7036);
    }

    @Test
    void solvePart1UsingSecondExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #################
                #...#...#...#..E#
                #.#.#.#.#.#.#.#.#
                #.#.#.#...#...#.#
                #.#.#.#.###.#.#.#
                #...#.#.#.....#.#
                #.#.#.#.#.#####.#
                #.#...#.#.#.....#
                #.#.#####.#.###.#
                #.#.#.......#...#
                #.#.###.#####.###
                #.#.#...#.....#.#
                #.#.#.#####.###.#
                #.#.#.........#.#
                #.#.#.#########.#
                #S#.............#
                #################
                """);

        // when
        var result = Day16.solvePart1(input);

        // then
        assertThat(result).isEqualTo(11048);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/16/input
        RawProblemInput input = RawProblemInput.fromResource("/day16.txt");

        // when
        var result = Day16.solvePart1(input);

        // then
        assertThat(result).isEqualTo(72400);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ###############
                #.......#....E#
                #.#.###.#.###.#
                #.....#.#...#.#
                #.###.#####.#.#
                #.#.#.......#.#
                #.#.#####.###.#
                #...........#.#
                ###.#.#####.#.#
                #...#.....#.#.#
                #.#.#.###.#.#.#
                #.....#...#.#.#
                #.###.#.#.#.#.#
                #S..#.....#...#
                ###############
                """);

        // when
        var result = Day16.solvePart2(input);

        // then
        assertThat(result).isEqualTo(45);
    }

    @Test
    void solvePart2UsingSecondExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #################
                #...#...#...#..E#
                #.#.#.#.#.#.#.#.#
                #.#.#.#...#...#.#
                #.#.#.#.###.#.#.#
                #...#.#.#.....#.#
                #.#.#.#.#.#####.#
                #.#...#.#.#.....#
                #.#.#####.#.###.#
                #.#.#.......#...#
                #.#.###.#####.###
                #.#.#...#.....#.#
                #.#.#.#####.###.#
                #.#.#.........#.#
                #.#.#.#########.#
                #S#.............#
                #################
                """);

        // when
        var result = Day16.solvePart2(input);

        // then
        assertThat(result).isEqualTo(64);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day16.txt");

        // when
        var result = Day16.solvePart2(input);

        // then
        assertThat(result).isEqualTo(435);
    }

}

