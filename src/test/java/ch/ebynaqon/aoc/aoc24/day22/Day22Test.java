package ch.ebynaqon.aoc.aoc24.day22;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/22
class Day22Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                15887950
                16495136
                527345
                704524
                """);

        // when
        var actual = Day22.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new SecretNumber(15887950),
                new SecretNumber(16495136),
                new SecretNumber(527345),
                new SecretNumber(704524)
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                1
                10
                100
                2024
                """);

        // when
        var result = Day22.solvePart1(input);

        // then
        assertThat(result).isEqualTo(37327623);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/22/input
        RawProblemInput input = RawProblemInput.fromResource("/day22.txt");

        // when
        var result = Day22.solvePart1(input);

        // then
        assertThat(result).isEqualTo(20506453102L);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                1
                2
                3
                2024
                """);

        // when
        var result = Day22.solvePart2(input);

        // then
        assertThat(result).isEqualTo(23);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day22.txt");

        // when
        var result = Day22.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @ParameterizedTest
    @MethodSource("secretNumbers2000")
    void secretGeneration2000(long initialSecret, long secret2000) {
        SecretNumber secret = new SecretNumber(initialSecret);

        for (int i = 0; i < 2000; i++) {
            secret = secret.next();
        }

        assertThat(secret).isEqualTo(new SecretNumber(secret2000));
    }

    public static Stream<Arguments> secretNumbers2000() {
        return Stream.of(
                Arguments.arguments(1, 8685429),
                Arguments.arguments(10, 4700978),
                Arguments.arguments(100, 15273692),
                Arguments.arguments(2024, 8667524)
        );
    }

    @ParameterizedTest
    @MethodSource("secretNumbers")
    void secretGeneration(long current, long next) {
        assertThat(new SecretNumber(current).next()).isEqualTo(new SecretNumber(next));
    }

    public static Stream<Arguments> secretNumbers() {
        return Stream.of(
                Arguments.arguments(15887950, 16495136),
                Arguments.arguments(16495136, 527345),
                Arguments.arguments(527345, 704524),
                Arguments.arguments(704524, 1553684),
                Arguments.arguments(1553684, 12683156),
                Arguments.arguments(12683156, 11100544),
                Arguments.arguments(11100544, 12249484),
                Arguments.arguments(12249484, 7753432),
                Arguments.arguments(7753432, 5908254)
        );
    }
}

