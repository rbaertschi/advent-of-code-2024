package ch.ebynaqon.aoc.aoc24.day13;

import java.util.Optional;

record Game(Vector buttonA, Vector buttonB, Vector prize) {
    public Optional<Integer> minTokensToWin() {
        // xA * i + xB * j = xP  ->  (xP - xB * j) / xA = i
        // yA * i + yB * j = yP. ->  yA * (xP - xB * j) / xA + yB * j = yP
        // yA * (xP - xB * j) / xA + yB * j = yP
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i * buttonA.x() + j * buttonB.x() == prize.x()
                    && i * buttonA.y() + j * buttonB.y() == prize.y()) {
                    result = Math.min(result, i * 3 + j);
                }
            }
        }
        System.out.println(result);
        return result == Integer.MAX_VALUE ? Optional.empty() : Optional.of(result);
    }
}

