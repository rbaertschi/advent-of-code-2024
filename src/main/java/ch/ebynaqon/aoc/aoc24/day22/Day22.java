package ch.ebynaqon.aoc.aoc24.day22;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        TradesByPriceChange tradeTable = new TradesByPriceChange();
        for (SecretNumber trader : problem.traders()) {
            updateTrades(trader, tradeTable);
        }
        return tradeTable.maxValue();
    }

    static void updateTrades(SecretNumber initialSecret, TradesByPriceChange tradeTable) {
        Set<PriceChangeSequence> seen = new HashSet<>();
        SecretNumber secret = initialSecret;
        PriceHistory priceHistory = new PriceHistory(-1, -1, -1, priceFromSecret(initialSecret));
        for (int i = 0; i < 2000; i++) {
            secret = secret.next();
            int currentPrice = priceFromSecret(secret);
            if (priceHistory.isValid()) {
                PriceChangeSequence priceChange = priceHistory.changes(currentPrice);
                if (!seen.contains(priceChange)) {
                    seen.add(priceChange);
                    tradeTable.add(priceChange, currentPrice);
                }
            }
            priceHistory = priceHistory.next(currentPrice);
        }
    }

    private static short priceFromSecret(SecretNumber secret) {
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

record PriceChangeSequence(int d1, int d2, int d3, int d4) {
}

record PriceHistory(int p1, int p2, int p3, int p4) {

    public PriceHistory next(int newPrice) {
        return new PriceHistory(p2, p3, p4, newPrice);
    }

    public boolean isValid() {
        return p1 > -1;
    }

    public PriceChangeSequence changes(int newPrice) {
        return new PriceChangeSequence(
                p2 - p1,
                p3 - p2,
                p4 - p3,
                newPrice - p4
        );
    }
}

class TradesByPriceChange {

    private final int[][][][] table;
    private int max;

    public TradesByPriceChange() {
        table = new int[19][19][19][19];
        max = 0;
    }

    public void add(PriceChangeSequence priceChanges, Integer bananas) {
        Index index = index(priceChanges);
        int current = table[index.i1()][index.i2()][index.i3()][index.i4()];
        int next = current + bananas;
        table[index.i1()][index.i2()][index.i3()][index.i4()] = next;
        if (next > max) {
            max = next;
        }
    }

    private static Index index(PriceChangeSequence priceChanges) {
        return new Index(idx(priceChanges.d1()), idx(priceChanges.d2()), idx(priceChanges.d3()), idx(priceChanges.d4()));
    }

    private static int idx(int i) {
        int idx = i + 9;
        if (idx < 0 || idx > 18) {
            throw new IndexOutOfBoundsException(idx);
        }
        return idx;
    }

    public int maxValue() {
        return max;
    }

    record Index(int i1, int i2, int i3, int i4) {
    }
}

