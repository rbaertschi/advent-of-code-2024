package ch.ebynaqon.aoc.aoc24.day21;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.DOWN;
import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.LEFT;
import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.PRESS;
import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.RIGHT;
import static ch.ebynaqon.aoc.aoc24.day21.MovementKey.UP;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.EIGHT;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.ENTER;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.FIVE;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.FOUR;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.NINE;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.ONE;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.SEVEN;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.SIX;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.THREE;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.TWO;
import static ch.ebynaqon.aoc.aoc24.day21.NumericKey.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/21
class Day21Test {

    @Test
    void numericKeyPresses() {
        /*
        +---+---+---+
        | 7 | 8 | 9 |
        +---+---+---+
        | 4 | 5 | 6 |
        +---+---+---+
        | 1 | 2 | 3 |
        +---+---+---+
        |   | 0 | A |
        +---+---+---+
        */
        assertThat(SEVEN.movementsToNextPress(NINE)).containsExactly(RIGHT, RIGHT, PRESS);
        assertThat(NINE.movementsToNextPress(SEVEN)).containsExactly(LEFT, LEFT, PRESS);
        assertThat(ONE.movementsToNextPress(ENTER)).containsExactly(DOWN, RIGHT, RIGHT, PRESS);
        assertThat(ENTER.movementsToNextPress(ONE)).containsExactly(UP, LEFT, LEFT, PRESS);
    }

    @Test
    void movementKeyPress() {
        /*
        +---+---+---+
        |   | ^ | A |
        +---+---+---+
        | < | v | > |
        +---+---+---+
        */
        assertThat(PRESS.movementsToNextPress(UP)).containsExactly(LEFT, PRESS);
        assertThat(UP.movementsToNextPress(PRESS)).containsExactly(RIGHT, PRESS);
        assertThat(LEFT.movementsToNextPress(UP)).containsExactly(UP, RIGHT, PRESS);
        assertThat(UP.movementsToNextPress(LEFT)).containsExactly(DOWN, LEFT, PRESS);
    }

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                029A
                980A
                179A
                456A
                379A
                """);

        // when
        var actual = Day21.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Code(List.of(ZERO, TWO, NINE, ENTER)),
                new Code(List.of(NINE, EIGHT, ZERO, ENTER)),
                new Code(List.of(ONE, SEVEN, NINE, ENTER)),
                new Code(List.of(FOUR, FIVE, SIX, ENTER)),
                new Code(List.of(THREE, SEVEN, NINE, ENTER))
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                029A
                980A
                179A
                456A
                379A
                """);

        // when
        var result = Day21.solvePart1(input);

        // then
        assertThat(result).isEqualTo(126384);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/21/input
        RawProblemInput input = RawProblemInput.fromResource("/day21.txt");

        // when
        var result = Day21.solvePart1(input);

        // then
        assertThat(result).isEqualTo(176452);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day21.txt");

        // when
        var result = Day21.solvePart2(input);

        // then
        assertThat(result).isEqualTo(218309335714068L);
    }

}

