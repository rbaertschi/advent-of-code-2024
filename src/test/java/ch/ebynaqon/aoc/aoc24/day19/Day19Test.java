package ch.ebynaqon.aoc.aoc24.day19;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/19
class Day19Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                r, wr, b, g, bwu, rb, gb, br
                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """);

        // when
        var actual = Day19.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                "r", "wr", "b", "g", "bwu", "rb", "gb", "br"
        ), List.of(
                "brwrr",
                "bggr",
                "gbbr",
                "rrbgbr",
                "ubwu",
                "bwurrg",
                "brgr",
                "bbrgwb"
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                r, wr, b, g, bwu, rb, gb, br
                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """);

        // when
        var result = Day19.solvePart1(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/19/input
        RawProblemInput input = RawProblemInput.fromResource("/day19.txt");

        // when
        var result = Day19.solvePart1(input);

        // then
        assertThat(result).isEqualTo(209);
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                r, wr, b, g, bwu, rb, gb, br
                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """);

        // when
        var result = Day19.solvePart2(input);

        // then
        assertThat(result).isEqualTo(16);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day19.txt");

        // when
        var result = Day19.solvePart2(input);

        // then
        assertThat(result).isEqualTo(777669668613191L);
    }

}

