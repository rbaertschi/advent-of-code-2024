package ch.ebynaqon.aoc.aoc24.day17;

import ch.ebynaqon.aoc.helper.RawProblemInput;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

// Solving puzzle https://adventofcode.com/2024/day/17
class Day17Test {

    @Test
    void parseProblemInput() {
        // given
        RawProblemInput input = new RawProblemInput("""
                Register A: 729
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
                """);

        // when
        var actual = Day17.parseProblem(input);

        // then
        assertThat(actual).isEqualTo(new ProblemInput(new Register(729, 0, 0, 0), List.of(
                (short) 0, (short) 1, (short) 5, (short) 4, (short) 3, (short) 0
        )));
    }

    @Test
    void solvePart1UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                Register A: 729
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
                """);

        // when
        var result = Day17.solvePart1(input);

        // then
        assertThat(result).isEqualTo("4,6,3,5,6,3,5,2,1,0");
    }

    @Test
    void solvePart1() {
        // given input from https://adventofcode.com/2024/day/17/input
        RawProblemInput input = RawProblemInput.fromResource("/day17.txt");

        // when
        var result = Day17.solvePart1(input);

        // then
        assertThat(result).isEqualTo("7,3,5,7,5,7,4,3,0");
    }

    @Test
    void solvePart2UsingExample() {
        // given
        RawProblemInput input = new RawProblemInput("""
                Register A: 2024
                Register B: 0
                Register C: 0
                
                Program: 0,3,5,4,3,0
                """);

        // when
        var result = Day17.solvePart2BruteForce(input);

        // then
        assertThat(result).isEqualTo(117440);
    }

    @Test
    void solvePart2() {
        // given
        RawProblemInput input = RawProblemInput.fromResource("/day17.txt");

        // when
        var result = Day17.solvePart2Analytically(input);

        // then
        assertThat(result).isEqualTo(105734774294938L);
    }

    @Nested
    class Instructions {

        @Test
        void adv_withLiteralOperand_yieldsDividedValue() {
            Register input = new Register(12, 4, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new ADV((short) 2).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(3, 4, 5, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void adv_withComboOperandForRegisterB_yieldsDividedValue() {
            Register input = new Register(16, 3, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new ADV((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(2, 3, 5, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void bxl_withLiteral1111_yieldsXORValueWithRegisterB() {
            Register input = new Register(0, 0b0110, 0, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BXL((short) 0b1111).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, 0b1001, 0, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @ParameterizedTest
        @ValueSource(shorts = {0, 1, 2, 3})
        void bst_withLiteralOperand_yieldsItsValueModulo8InRegisterB(short literalOperand) {
            Register input = new Register(0, 15, 0, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BST(literalOperand).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, literalOperand, 0, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void bst_withComboOperandForRegisterB_yieldsRegisterBModulo8() {
            Register input = new Register(0, 15, 0, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BST((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, 7, 0, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void jnz_withRegisterABeingZero_doesNothing() {
            Register input = new Register(0, 1, 2, 4);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new JNZ((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, 1, 2, 6));
            assertThat(outputDevice.output()).isEmpty();
        }

        @ParameterizedTest
        @MethodSource("nonZeroRegisterAWithLiteralValues")
        void jnz_withRegisterABeingNonZero_movesInstructionPointerToLiteralValue(int a, short literalOperand) {
            Register input = new Register(a, 1, 2, 4);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new JNZ(literalOperand).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(a, 1, 2, literalOperand));
            assertThat(outputDevice.output()).isEmpty();
        }

        public static Stream<Arguments> nonZeroRegisterAWithLiteralValues() {
            return Stream.of(
                    arguments(1, (short) 0),
                    arguments(23, (short) 1),
                    arguments(7, (short) 2),
                    arguments(7, (short) 3),
                    arguments(2, (short) 4),
                    arguments(1234, (short) 5),
                    arguments(9, (short) 6)
            );
        }

        @ParameterizedTest
        @ValueSource(shorts = {0, 1, 2, 3, 4, 5, 6, 7})
        void bxc_withAnyOperand_yieldsXOROfBAndCInRegisterB(short ignoredOperand) {
            Register input = new Register(0, 0b10101010, 0b11111111, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BXC(ignoredOperand).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, 0b01010101, 0b11111111, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @ParameterizedTest
        @MethodSource("bitPatterns")
        void bxc_yieldsXOROfBAndCInRegisterB(int b, int c, int newB) {
            Register input = new Register(0, b, c, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BXC(0).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(0, newB, c, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        public static Stream<Arguments> bitPatterns() {
            return Stream.of(
                    arguments(0b10101010, 0b11111111, 0b01010101),
                    arguments(0b10101010, 0b11001100, 0b01100110),
                    arguments(0b00001111, 0b00111100, 0b00110011),
                    arguments(0b11111111, 0b11111111, 0b00000000),
                    arguments(0b000000, 0b000000, 0b000000)
            );
        }

        @ParameterizedTest
        @ValueSource(shorts = {0, 1, 2, 3})
        void out_withLiteralOperand_yieldsLiteralValueInOutput(short literalOperand) {
            Register input = new Register(1, 2, 3, 11);
            OutputDevice outputDevice = new OutputDevice(new ArrayList<>(Arrays.asList(42, 1)));

            Register result = new OUT(literalOperand).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(1, 2, 3, 13));
            assertThat(outputDevice.output()).isEqualTo("42,1," + literalOperand);
        }

        @Test
        void out_withComboOperandForRegisterA_yieldsValueOfRegisterAInOutput() {
            Register input = new Register(1, 2, 3, 11);
            OutputDevice outputDevice = new OutputDevice(new ArrayList<>(Arrays.asList(42, 1)));

            Register result = new OUT((short) 4).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(1, 2, 3, 13));
            assertThat(outputDevice.output()).isEqualTo("42,1,1");
        }

        @Test
        void out_withComboOperandForRegisterB_yieldsValueOfRegisterBInOutput() {
            Register input = new Register(1, 2, 3, 11);
            OutputDevice outputDevice = new OutputDevice(new ArrayList<>(Arrays.asList(42, 1)));

            Register result = new OUT((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(1, 2, 3, 13));
            assertThat(outputDevice.output()).isEqualTo("42,1,2");
        }

        @Test
        void out_withComboOperandForRegisterC_yieldsValueOfRegisterCInOutput() {
            Register input = new Register(1, 2, 3, 11);
            OutputDevice outputDevice = new OutputDevice(new ArrayList<>(Arrays.asList(42, 1)));

            Register result = new OUT((short) 6).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(1, 2, 3, 13));
            assertThat(outputDevice.output()).isEqualTo("42,1,3");
        }

        @Test
        void bdv() {
            Register input = new Register(1, 2, 3, 11);
            OutputDevice outputDevice = new OutputDevice(new ArrayList<>(Arrays.asList(42, 1)));

            Register result = new OUT((short) 6).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(1, 2, 3, 13));
            assertThat(outputDevice.output()).isEqualTo("42,1,3");
        }

        @Test
        void bdv_withLiteralOperand_yieldsDividedValue() {
            Register input = new Register(12, 4, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BDV((short) 2).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(12, 3, 5, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void bdv_withComboOperandForRegisterB_yieldsDividedValue() {
            Register input = new Register(16, 3, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new BDV((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(16, 2, 5, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void cdv_withLiteralOperand_yieldsDividedValue() {
            Register input = new Register(12, 4, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new CDV((short) 2).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(12, 4, 3, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

        @Test
        void cdv_withComboOperandForRegisterB_yieldsDividedValue() {
            Register input = new Register(16, 3, 5, 0);
            OutputDevice outputDevice = new OutputDevice();

            Register result = new CDV((short) 5).process(input, outputDevice);

            assertThat(result).isEqualTo(new Register(16, 3, 2, 2));
            assertThat(outputDevice.output()).isEmpty();
        }

    }

}

