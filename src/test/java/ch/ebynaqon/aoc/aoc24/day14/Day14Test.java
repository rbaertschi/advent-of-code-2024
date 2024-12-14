package ch.ebynaqon.aoc.aoc24.day14;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/14
class Day14Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                """);

        // when
        var actual = Day14.parseProblem(input, 11, 7);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Robot(new Position(0, 4), new Velocity(3, -3)),
                new Robot(new Position(6, 3), new Velocity(-1, -3)),
                new Robot(new Position(10, 3), new Velocity(-1, 2)),
                new Robot(new Position(2, 0), new Velocity(2, -1)),
                new Robot(new Position(0, 0), new Velocity(1, 3))
        ), 11, 7));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
                """);

        // when
        var result = Day14.solvePart1(input, 11, 7, 100);

        // then
        assertThat(result).isEqualTo(12);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/14/input
        RawProblemInput input = RawProblemInput.fromResource("/day14.txt");

        // when
        var result = Day14.solvePart1(input, 101, 103, 100);

        // then
        assertThat(result).isEqualTo(218619120);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day14.txt");

        // when
        var result = Day14.solvePart2(input, 101, 103);

        // then
        assertThat(result).isEqualTo(7055);
    }

}

