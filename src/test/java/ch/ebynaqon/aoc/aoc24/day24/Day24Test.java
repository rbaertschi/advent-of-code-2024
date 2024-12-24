package ch.ebynaqon.aoc.aoc24.day24;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// Solving puzzle https://adventofcode.com/2024/day/24
class Day24Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                x00: 1
                x01: 1
                x02: 1
                y00: 0
                y01: 1
                y02: 0
                
                x00 AND y00 -> z00
                x01 XOR y01 -> z01
                x02 OR y02 -> z02
                """);

        // when
        var actual = Day24.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(Map.of(
                "x00", WireState.TRUE,
                "x01", WireState.TRUE,
                "x02", WireState.TRUE,
                "y00", WireState.FALSE,
                "y01", WireState.TRUE,
                "y02", WireState.FALSE
        ),
                List.of(
                        new AndGate("x00", "y00", "z00"),
                        new XorGate("x01", "y01", "z01"),
                        new OrGate("x02", "y02", "z02")
                )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                x00: 1
                x01: 1
                x02: 1
                y00: 0
                y01: 1
                y02: 0
                
                x00 AND y00 -> z00
                x01 XOR y01 -> z01
                x02 OR y02 -> z02
                """);

        // when
        var result = Day24.solvePart1(input);

        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    void solvePart1UsingLargerExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                x00: 1
                x01: 0
                x02: 1
                x03: 1
                x04: 0
                y00: 1
                y01: 1
                y02: 1
                y03: 1
                y04: 1
                
                ntg XOR fgs -> mjb
                y02 OR x01 -> tnw
                kwq OR kpj -> z05
                x00 OR x03 -> fst
                tgd XOR rvg -> z01
                vdt OR tnw -> bfw
                bfw AND frj -> z10
                ffh OR nrd -> bqk
                y00 AND y03 -> djm
                y03 OR y00 -> psh
                bqk OR frj -> z08
                tnw OR fst -> frj
                gnj AND tgd -> z11
                bfw XOR mjb -> z00
                x03 OR x00 -> vdt
                gnj AND wpb -> z02
                x04 AND y00 -> kjc
                djm OR pbm -> qhw
                nrd AND vdt -> hwm
                kjc AND fst -> rvg
                y04 OR y02 -> fgs
                y01 AND x02 -> pbm
                ntg OR kjc -> kwq
                psh XOR fgs -> tgd
                qhw XOR tgd -> z09
                pbm OR djm -> kpj
                x03 XOR y03 -> ffh
                x00 XOR y04 -> ntg
                bfw OR bqk -> z06
                nrd XOR fgs -> wpb
                frj XOR qhw -> z04
                bqk OR frj -> z07
                y03 OR x01 -> nrd
                hwm AND bqk -> z03
                tgd XOR rvg -> z12
                tnw OR pbm -> gnj
                """);

        // when
        var result = Day24.solvePart1(input);

        // then
        assertThat(result).isEqualTo(2024);
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/24/input
        RawProblemInput input = RawProblemInput.fromResource("/day24.txt");

        // when
        var result = Day24.solvePart1(input);

        // then
        assertThat(result).isEqualTo(46362252142374L);
    }

    @Test
    @Disabled
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                42
                """);

        // when
        var result = Day24.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

    @Test
    @Disabled
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day24.txt");

        // when
        var result = Day24.solvePart2(input);

        // then
        assertThat(result).isEqualTo(42);
    }

}

