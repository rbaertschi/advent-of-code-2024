package ch.ebynaqon.aoc.aoc24.day18;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/18
class Day18Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                5,4
                4,2
                4,5
                3,0
                2,1
                6,3
                2,4
                1,5
                0,6
                3,3
                2,6
                5,1
                1,2
                5,5
                2,5
                6,5
                1,4
                0,4
                6,4
                1,1
                6,1
                1,0
                0,5
                1,6
                2,0
                """);

        // when
        var actual = Day18.parseProblem(input, 7, 7);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Position(5, 4),
                new Position(4, 2),
                new Position(4, 5),
                new Position(3, 0),
                new Position(2, 1),
                new Position(6, 3),
                new Position(2, 4),
                new Position(1, 5),
                new Position(0, 6),
                new Position(3, 3),
                new Position(2, 6),
                new Position(5, 1),
                new Position(1, 2),
                new Position(5, 5),
                new Position(2, 5),
                new Position(6, 5),
                new Position(1, 4),
                new Position(0, 4),
                new Position(6, 4),
                new Position(1, 1),
                new Position(6, 1),
                new Position(1, 0),
                new Position(0, 5),
                new Position(1, 6),
                new Position(2, 0)
        ), 7, 7));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                5,4
                4,2
                4,5
                3,0
                2,1
                6,3
                2,4
                1,5
                0,6
                3,3
                2,6
                5,1
                1,2
                5,5
                2,5
                6,5
                1,4
                0,4
                6,4
                1,1
                6,1
                1,0
                0,5
                1,6
                2,0
                """);

        // when
        var result = Day18.solvePart1(input, 7, 7, 12);

        // then
        assertThat(result).isEqualTo(22);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/18/input
        RawProblemInput input = RawProblemInput.fromResource("/day18.txt");

        // when
        var result = Day18.solvePart1(input, 71, 71, 1024);

        // then
        assertThat(result).isEqualTo(320);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day18.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day18.txt");

        // when
        var result = Day18.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

