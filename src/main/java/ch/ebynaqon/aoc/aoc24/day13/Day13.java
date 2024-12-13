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

    static ProblemInput parseProblem(RawProblemInput input) {
        List<Game> games = Arrays.stream(input.getWholeInput().split("\\n\\n"))
                .map(String::trim)
                .map(Day13::parseGame)
                .toList();
        return new ProblemInput(games);
    }

    private static Game parseGame(String input) {
        Matcher matcher = GAME_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
        Vector buttonA = new Vector(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        Vector buttonB = new Vector(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        Vector prize = new Vector(Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        return new Game(buttonA, buttonB, prize);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.games().stream().map(Game::minTokensToWin).filter(Optional::isPresent).mapToLong(Optional::get).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

