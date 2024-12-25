package ch.ebynaqon.aoc.aoc24.day25;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/25
class Day25Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #####
                .####
                .####
                .####
                .#.#.
                .#...
                .....
                
                #####
                ##.##
                .#.##
                ...##
                ...#.
                ...#.
                .....
                
                .....
                #....
                #....
                #...#
                #.#.#
                #.###
                #####
                
                .....
                .....
                #.#..
                ###..
                ###.#
                ###.#
                #####
                
                .....
                .....
                .....
                #....
                #.#..
                #.#.#
                #####
                """);

        // when
        var actual = Day25.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Lock(List.of(0,5,3,4,3)),
                new Lock(List.of(1,2,0,5,3))
        ), List.of(
                new Key(List.of(0,5,3,4,2)),
                new Key(List.of(1,2,1,5,3)),
                new Key(List.of(2,5,3,5,4))
        ), 5));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                #####
                .####
                .####
                .####
                .#.#.
                .#...
                .....
                
                #####
                ##.##
                .#.##
                ...##
                ...#.
                ...#.
                .....
                
                .....
                #....
                #....
                #...#
                #.#.#
                #.###
                #####
                
                .....
                .....
                #.#..
                ###..
                ###.#
                ###.#
                #####
                
                .....
                .....
                .....
                #....
                #.#..
                #.#.#
                #####
                """);

        // when
        var result = Day25.solvePart1(input);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/25/input
        RawProblemInput input = RawProblemInput.fromResource("/day25.txt");

        // when
        var result = Day25.solvePart1(input);

        // then
        assertThat(result).isEqualTo(3021);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day25.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day25.txt");

        // when
        var result = Day25.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

