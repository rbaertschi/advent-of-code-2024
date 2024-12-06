package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/6
class Day06Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """);

        // when
        var actual = Day06.parseProblem(input);

        // then
        assertThat(actual.rows()).isEqualTo(10);
        assertThat(actual.cols()).isEqualTo(10);
        assertThat(actual.guard()).isEqualTo(new Guard(new Position(6, 4), Direction.UP));
        assertThat(actual.getObstacles()).isEqualTo(new boolean[][]{
                {false, false, false, false, true, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, true, false},
                {true, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false},
        });
    }

    @Test
    void solvePart1UsingExample() throws LoopDetectedException {
        // given
        RawProblemInput input = new RawProblemInput("""
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """);

        // when
        var result = Day06.solvePart1(input);

        // then
        assertThat(result).isEqualTo(41);
    }

    @Test
    void solvePart1() throws LoopDetectedException {
        // given input from https://adventofcode.com/2024/day/6/input
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart1(input);

        // then
        assertThat(result).isEqualTo(5095);
    }

    @Test
    void solvePart2UsingExample() throws LoopDetectedException {
        // given
        RawProblemInput input = new RawProblemInput("""
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """);

        // when
        var result = Day06.solvePart2(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void solvePart2() throws LoopDetectedException {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart2(input);

        // then
        assertThat(result).isEqualTo(1933);
    }

}

