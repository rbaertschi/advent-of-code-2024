package ch.ebynaqon.aoc.aoc24.day12;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/12
class Day12Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                AAAA
                BBCD
                BBCC
                EEEC
                """);

        // when
        var actual = Day12.parseProblem(input);

        // then
        assertThat(actual.plots()).isEqualTo(new GardenPlot[][]{
                {new GardenPlot('A', new Position(0, 0)), new GardenPlot('A', new Position(0, 1)), new GardenPlot('A', new Position(0, 2)), new GardenPlot('A', new Position(0, 3))},
                {new GardenPlot('B', new Position(1, 0)), new GardenPlot('B', new Position(1, 1)), new GardenPlot('C', new Position(1, 2)), new GardenPlot('D', new Position(1, 3))},
                {new GardenPlot('B', new Position(2, 0)), new GardenPlot('B', new Position(2, 1)), new GardenPlot('C', new Position(2, 2)), new GardenPlot('C', new Position(2, 3))},
                {new GardenPlot('E', new Position(3, 0)), new GardenPlot('E', new Position(3, 1)), new GardenPlot('E', new Position(3, 2)), new GardenPlot('C', new Position(3, 3))},
        });
    }

    @Test
    void solvePart1UsingSmallExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                AAAA
                BBCD
                BBCC
                EEEC
                """);

        // when
        var result = Day12.solvePart1(input);

        // then
        assertThat(result).isEqualTo(140);
    }

    @Test
    void solvePart1UsingBiggerExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE
                """);

        // when
        var result = Day12.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1930);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/12/input
        RawProblemInput input = RawProblemInput.fromResource("/day12.txt");

        // when
        var result = Day12.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1465968);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day12.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day12.txt");

        // when
        var result = Day12.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

