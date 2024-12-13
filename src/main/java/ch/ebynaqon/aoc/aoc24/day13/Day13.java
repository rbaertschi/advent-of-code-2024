package ch.ebynaqon.aoc.aoc24.day13;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Day13 {

    Pattern GAME_PATTERN = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)\\n" +
                                           "Button B: X\\+(\\d+), Y\\+(\\d+)\\n" +
                                           "Prize: X=(\\d+), Y=(\\d+)");

    static ProblemInput parseProblem(RawProblemInput input, long prizeOffset) {
        List<Game> games = Arrays.stream(input.getWholeInput().split("\\n\\n"))
                .map(String::trim)
                .map(gameInput -> parseGame(gameInput, prizeOffset))
                .toList();
        return new ProblemInput(games);
    }

    private static Game parseGame(String input, long prizeOffset) {
        Matcher matcher = GAME_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
        Vector buttonA = new Vector(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)));
        Vector buttonB = new Vector(Long.parseLong(matcher.group(3)), Long.parseLong(matcher.group(4)));
        Vector prize = new Vector(Long.parseLong(matcher.group(5)) + prizeOffset, Long.parseLong(matcher.group(6)) + prizeOffset);
        return new Game(buttonA, buttonB, prize);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input, 0);
        return problem.games().stream()
                .map(Game::minTokensToWin)
                .filter(Optional::isPresent)
                .mapToLong(Optional::get)
                .sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input, 10000000000000L);
        return problem.games().stream()
                .map(Game::minTokensToWin)
                .filter(Optional::isPresent)
                .mapToLong(Optional::get)
                .sum();
    }
}

