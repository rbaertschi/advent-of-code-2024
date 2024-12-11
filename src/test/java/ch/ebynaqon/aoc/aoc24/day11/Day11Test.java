package ch.ebynaqon.aoc.aoc24.day11;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/11
class Day11Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                125 17
                """);

        // when
        var actual = Day11.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(Map.of(
                125L, 1L,
                17L, 1L
        ));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                125 17
                """);

        // when
        var result = Day11.solvePart1(input);

        // then
        assertThat(result).isEqualTo(55312);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/11/input
        RawProblemInput input = RawProblemInput.fromResource("/day11.txt");

        // when
        var result = Day11.solvePart1(input);

        // then
        assertThat(result).isEqualTo(199753);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day11.txt");

        // when
        var result = Day11.solvePart2(input);

        // then
        assertThat(result).isEqualTo(new BigInteger("239413123020116"));
    }

    @ParameterizedTest
    @MethodSource
    void numberOfDigits(int value, int numberOfDigits) {
        assertThat(Day11.numberOfDigits(value)).isEqualTo(numberOfDigits);
    }

    static Stream<Arguments> numberOfDigits() {
        return Stream.of(
                Arguments.arguments(1,1),
                Arguments.arguments(7,1),
                Arguments.arguments(13,2),
                Arguments.arguments(23,2),
                Arguments.arguments(468,3),
                Arguments.arguments(8432,4),
                Arguments.arguments(345678,6)
        );
    }
}

