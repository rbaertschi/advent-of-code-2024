package ch.ebynaqon.aoc.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Template {
    public static void main(String[] args) throws IOException {
        int day = 21;
        String dayWithLeadingZero = String.format("%02d", day);
        Path javaSourcePackage = Path.of("src/main/java/ch/ebynaqon/aoc/aoc24/day" + dayWithLeadingZero);
        Path javaTestPackage = Path.of("src/test/java/ch/ebynaqon/aoc/aoc24/day" + dayWithLeadingZero);
        if (javaSourcePackage.toFile().exists()) {
            System.out.printf("Path %s already exists!%n", javaSourcePackage);
            return;
        }
        if (!javaSourcePackage.toFile().mkdirs() || !javaTestPackage.toFile().mkdirs()) {
            System.out.println("Failed to create packages");
            return;
        }
        Path testResource = Path.of("src/test/resources/day" + dayWithLeadingZero + ".txt");
        Files.writeString(testResource, "42");
        Files.writeString(javaTestPackage.resolve("Day" + dayWithLeadingZero + "Test.java"), """
                package ch.ebynaqon.aoc.aoc24.day{dayWithLeadingZero};
                
                import ch.ebynaqon.aoc.helper.RawProblemInput;
                import org.junit.jupiter.api.Disabled;
                import org.junit.jupiter.api.Test;
                
                import java.util.List;
                
                import static org.assertj.core.api.Assertions.assertThat;
                
                // Solving puzzle https://adventofcode.com/2024/day/{day}
                class Day{dayWithLeadingZero}Test {
                
                    @Test
                    void parseProblemInput() {
                        // given
                        RawProblemInput input = new RawProblemInput(""\"
                                42
                                ""\");
                
                        // when
                        var actual = Day{dayWithLeadingZero}.parseProblem(input);
                
                        // then
                        assertThat(actual).isEqualTo(new ProblemInput(List.of(
                                new ProblemSample(42L)
                        )));
                    }
                
                    @Test
                    @Disabled
                    void solvePart1UsingExample() {
                        // given
                        RawProblemInput input = new RawProblemInput(""\"
                                42
                                ""\");
                
                        // when
                        var result = Day{dayWithLeadingZero}.solvePart1(input);
                
                        // then
                        assertThat(result).isEqualTo(42);
                    }
                
                    @Test
                    @Disabled
                    void solvePart1() {
                        // given input from https://adventofcode.com/2024/day/{day}/input
                        RawProblemInput input = RawProblemInput.fromResource("/day{dayWithLeadingZero}.txt");
                
                        // when
                        var result = Day{dayWithLeadingZero}.solvePart1(input);
                
                        // then
                        assertThat(result).isEqualTo(42);
                    }
                
                    @Test
                    @Disabled
                    void solvePart2UsingExample() {
                        // given
                        RawProblemInput input = new RawProblemInput(""\"
                                42
                                ""\");
                
                        // when
                        var result = Day{dayWithLeadingZero}.solvePart2(input);
                
                        // then
                        assertThat(result).isEqualTo(42);
                    }
                
                    @Test
                    @Disabled
                    void solvePart2() {
                        // given
                        RawProblemInput input = RawProblemInput.fromResource("/day{dayWithLeadingZero}.txt");
                
                        // when
                        var result = Day{dayWithLeadingZero}.solvePart2(input);
                
                        // then
                        assertThat(result).isEqualTo(42);
                    }
                
                }
                
                """.replaceAll("\\{dayWithLeadingZero}", dayWithLeadingZero).replaceAll("\\{day}", String.valueOf(day)));
        Files.writeString(javaSourcePackage.resolve("Day" + dayWithLeadingZero + ".java"), """
                package ch.ebynaqon.aoc.aoc24.day{dayWithLeadingZero};
                
                import ch.ebynaqon.aoc.helper.RawProblemInput;
                
                import java.util.List;
                
                interface Day{dayWithLeadingZero} {
                
                    static ProblemInput parseProblem(RawProblemInput input) {
                        List<ProblemSample> samples = input.getLines().stream().map(Day{dayWithLeadingZero}::parseLine).toList();
                        return new ProblemInput(samples);
                    }
                
                    private static ProblemSample parseLine(String input) {
                        return new ProblemSample(Long.parseLong(input));
                    }
                
                    static long solvePart1(RawProblemInput input) {
                        ProblemInput problem = parseProblem(input);
                        return problem.samples().stream().mapToLong(ProblemSample::value).min().orElseThrow();
                    }
                
                    static long solvePart2(RawProblemInput input) {
                        ProblemInput problem = parseProblem(input);
                        return problem.samples().stream().mapToLong(ProblemSample::value).max().orElseThrow();
                    }
                }
                
                """.replaceAll("\\{dayWithLeadingZero}", dayWithLeadingZero));
        Files.writeString(javaSourcePackage.resolve("ProblemInput.java"), """
                package ch.ebynaqon.aoc.aoc24.day{dayWithLeadingZero};
                
                import java.util.List;
                
                record ProblemInput(List<ProblemSample> samples) {
                }
                
                """.replaceAll("\\{dayWithLeadingZero}", dayWithLeadingZero));
        Files.writeString(javaSourcePackage.resolve("ProblemSample.java"), """
                package ch.ebynaqon.aoc.aoc24.day{dayWithLeadingZero};
                
                record ProblemSample(long value) {
                }
                
                """.replaceAll("\\{dayWithLeadingZero}", dayWithLeadingZero));
    }
}
