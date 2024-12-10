package ch.ebynaqon.aoc.aoc24.day10;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/10
class Day10Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
                """);

        // when
        var actual = Day10.parseProblem(input);


        // then
        assertThat(actual.rows()).isEqualTo(8);
        assertThat(actual.cols()).isEqualTo(8);
        assertThat(actual.heightMap()).isEqualTo(new int[][]{
                {8,9,0,1,0,1,2,3},
                {7,8,1,2,1,8,7,4},
                {8,7,4,3,0,9,6,5},
                {9,6,5,4,9,8,7,4},
                {4,5,6,7,8,9,0,3},
                {3,2,0,1,9,0,1,2},
                {0,1,3,2,9,8,0,1},
                {1,0,4,5,6,7,3,2}
        });
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
                """);

        // when
        var result = Day10.solvePart1(input);

        // then
        assertThat(result).isEqualTo(36);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/10/input
        RawProblemInput input = RawProblemInput.fromResource("/day10.txt");

        // when
        var result = Day10.solvePart1(input);

        // then
        assertThat(result).isEqualTo(552);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
                """);

        // when
        var result = Day10.solvePart2(input);

        // then
        assertThat(result).isEqualTo(81);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day10.txt");

        // when
        var result = Day10.solvePart2(input);

        // then
        assertThat(result).isEqualTo(1225);
    }

}

