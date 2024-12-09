package ch.ebynaqon.aoc.aoc24.day09;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/9
class Day09Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                2333133121414131402
                """);

        // when
        var actual = Day09.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Block(0, 2, 0),
                new Block(5, 3, 1),
                new Block(11, 1, 2),
                new Block(15, 3, 3),
                new Block(19, 2, 4),
                new Block(22, 4, 5),
                new Block(27, 4, 6),
                new Block(32, 3, 7),
                new Block(36, 4, 8),
                new Block(40, 2, 9)
        ), List.of(
                new FreeSpace(2, 3),
                new FreeSpace(8, 3),
                new FreeSpace(12, 3),
                new FreeSpace(18, 1),
                new FreeSpace(21, 1),
                new FreeSpace(26, 1),
                new FreeSpace(31, 1),
                new FreeSpace(35, 1),
                new FreeSpace(40, 0)
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                2333133121414131402
                """);

        // when
        var result = Day09.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1928);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/9/input
        RawProblemInput input = RawProblemInput.fromResource("/day09.txt");

        // when
        var result = Day09.solvePart1(input);

        // then
        assertThat(result).isEqualTo(6346871685398L);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day09.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day09.txt");

        // when
        var result = Day09.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

