package ch.ebynaqon.aoc.aoc24.day04;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/4
class Day04Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                123
                456
                789
                """);

        // when
        var actual = Day04.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(
                List.of("123", "456", "789"),
                List.of("147", "258", "369"),
                List.of("7", "48", "159","26","3"),
                List.of("9", "86", "753", "42", "1")
        ));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """);

        // when
        var result = Day04.solvePart1(input);

        // then
        assertThat(result).isEqualTo(18);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/4/input
        RawProblemInput input = RawProblemInput.fromResource("/day04.txt");

        // when
        var result = Day04.solvePart1(input);

        // then
        assertThat(result).isEqualTo(2639);
    }

    @Test
    void getCross() {
        List<String> lines = List.of(
                "123",
                "456",
                "789"
        );
        Day04.Cross cross = Day04.getCross(1, 1, lines);

        assertThat(cross).isEqualTo(new Day04.Cross("753","159"));
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """);

        // when
        var result = Day04.solvePart2(input);

        // then
        assertThat(result).isEqualTo(9);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day04.txt");

        // when
        var result = Day04.solvePart2(input);

        // then
        assertThat(result).isEqualTo(2005);
    }

}
