package ch.ebynaqon.aoc.aoc24.day06;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertThat(actual).isEqualTo(new ProblemInput(new Guard(new Position(6, 4), Direction.UP), List.of(
                new Position(0, 4),
                new Position(1, 9),
                new Position(3, 2),
                new Position(4, 7),
                new Position(6, 1),
                new Position(7, 8),
                new Position(8, 0),
                new Position(9, 6)
        ), 10, 10));
    }

    @Test
    void solvePart1UsingExample() throws LoopDetected {
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
    void solvePart1() throws LoopDetected {
        // given input from https://adventofcode.com/2024/day/6/input
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart1(input);

        // then
        assertThat(result).isEqualTo(5095);
    }

    @Test
    void solvePart2UsingExample() throws LoopDetected {
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
    void solvePart2() throws LoopDetected {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day06.txt");

        // when
        var result = Day06.solvePart2(input);

        // then
        assertThat(result).isEqualTo(1933);
    }

}

