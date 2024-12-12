package ch.ebynaqon.aoc.aoc24.day12;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/12
class Day12Test {

    public static Stream<Arguments> mapsAndCost() {
        return Stream.of(
                Arguments.arguments("""
                        AAAA
                        BBCD
                        BBCC
                        EEEC
                        """, 80),
                Arguments.arguments("""
                        EEEEE
                        EXXXX
                        EEEEE
                        EXXXX
                        EEEEE
                        """, 236),
                Arguments.arguments("""
                        AAAAAA
                        AAABBA
                        AAABBA
                        ABBAAA
                        ABBAAA
                        AAAAAA
                        """, 368),
                Arguments.arguments("""
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
                        """, 1206)
        );
    }

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

    @ParameterizedTest
    @MethodSource("mapsAndCost")
    void solvePart2UsingExamples(String map, int expectedCost) {
        // given
        RawProblemInput input = new RawProblemInput(map);

        // when
        var result = Day12.solvePart2(input);

        // then
        assertThat(result).isEqualTo(expectedCost);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day12.txt");

        // when
        var result = Day12.solvePart2(input);

        // then
        assertThat(result).isEqualTo(897702);
    }

}

