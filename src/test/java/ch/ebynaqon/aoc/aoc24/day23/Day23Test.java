package ch.ebynaqon.aoc.aoc24.day23;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/23
class Day23Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                kh-tc
                qp-kh
                de-cg
                ka-co
                """);

        // when
        var actual = Day23.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                new Connection("kh","tc"),
                new Connection("qp","kh"),
                new Connection("de","cg"),
                new Connection("ka","co")
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                kh-tc
                qp-kh
                de-cg
                ka-co
                yn-aq
                qp-ub
                cg-tb
                vc-aq
                tb-ka
                wh-tc
                yn-cg
                kh-ub
                ta-co
                de-co
                tc-td
                tb-wq
                wh-td
                ta-ka
                td-qp
                aq-cg
                wq-ub
                ub-vc
                de-ta
                wq-aq
                wq-vc
                wh-yn
                ka-de
                kh-ta
                co-tc
                wh-qp
                tb-vc
                td-yn
                """);

        // when
        var result = Day23.solvePart1(input);

        // then
        assertThat(result).isEqualTo(7);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/23/input
        RawProblemInput input = RawProblemInput.fromResource("/day23.txt");

        // when
        var result = Day23.solvePart1(input);

        // then
        assertThat(result).isEqualTo(1098);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day23.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day23.txt");

        // when
        var result = Day23.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

