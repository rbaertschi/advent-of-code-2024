package ch.ebynaqon.aoc.aoc24.day22;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day22 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<SecretNumber> samples = input.getLines().stream().map(Day22::parseLine).toList();
        return new ProblemInput(samples);
    }

    private static SecretNumber parseLine(String input) {
        return new SecretNumber(Long.parseLong(input));
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return problem.traders().stream()
                .map(Day22::get2000th)
                .mapToLong(SecretNumber::value).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<List<Trade>> tradesByTrader = problem.traders().stream().map(Day22::getTrades).toList();
        List<PriceChangeSequence> changes = tradesByTrader.stream().flatMap(t -> t.stream().map(Trade::changeSequence)).toList();
        int maxBananas = 0;
        for (PriceChangeSequence sequence : changes) {
            int bananas = tradesByTrader.stream().mapToInt(trades ->
                    trades.stream().filter(trade -> trade.changeSequence().equals(sequence))
                            .mapToInt(Trade::bananas).max().orElse(0)).sum();
            maxBananas = Math.max(maxBananas, bananas);
        }

        return maxBananas;
    }

    static List<Trade> getTrades(SecretNumber initialSecret) {
        ArrayList<Trade> trades = new ArrayList<>();
        SecretNumber secret = initialSecret;
        short bananas1 = -1;
        short bananas2 = -1;
        short bananas3 = -1;
        short bananas4 = bananas(initialSecret);
        for (int i = 0; i < 2000; i++) {
            secret = secret.next();
            short currentBananas = bananas(secret);
            if (bananas1 > -1) {
                PriceChangeSequence changeSequence = new PriceChangeSequence(
                        (short) (bananas2 - bananas1),
                        (short) (bananas3 - bananas2),
                        (short) (bananas4 - bananas3),
                        (short) (currentBananas - bananas4)
                );
                trades.add(new Trade(changeSequence, currentBananas));
            }
            bananas1 = bananas2;
            bananas2 = bananas3;
            bananas3 = bananas4;
            bananas4 = currentBananas;
        }
        return trades;
    }

    private static short bananas(SecretNumber secret) {
        return (short) (secret.value() % 10);
    }

    private static SecretNumber get2000th(SecretNumber secret) {
        SecretNumber result = secret;
        for (int i = 0; i < 2000; i++) {
            result = result.next();
        }
        return result;
    }

}

record ProblemInput(List<SecretNumber> traders) {
}

record SecretNumber(long value) {
    SecretNumber next() {
        /*
        - Calculate the result of multiplying the secret number by 64.
          Then, mix this result into the secret number.
          Finally, prune the secret number.
        - Calculate the result of dividing the secret number by 32.
          Round the result down to the nearest integer.
          Then, mix this result into the secret number.
          Finally, prune the secret number.
        - Calculate the result of multiplying the secret number by 2048.
          Then, mix this result into the secret number.
          Finally, prune the secret number.

        Each step of the above process involves mixing and pruning:
        - To mix a value into the secret number, calculate the bitwise XOR of the given value and the secret number.
          Then, the secret number becomes the result of that operation.
          (If the secret number is 42 and you were to mix 15 into the secret number, the secret number would become 37.)
        - To prune the secret number, calculate the value of the secret number modulo 16777216.
          Then, the secret number becomes the result of that operation.
          (If the secret number is 100000000 and you were to prune the secret number, the secret number would become 16113920.)
        */
        long secret = value;
        secret = prune(mix(secret * 64, secret));
        secret = prune(mix(secret / 32, secret));
        secret = prune(mix(secret * 2048, secret));
        return new SecretNumber(secret);
    }

    private long prune(long secret) {
        return secret % 16777216;
    }

    private long mix(long value, long secret) {
        return value ^ secret;
    }
}

record Trade(PriceChangeSequence changeSequence, short bananas) {}

record PriceChangeSequence(short d1, short d2, short d3, short d4) {}
