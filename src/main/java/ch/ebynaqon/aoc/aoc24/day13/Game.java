package ch.ebynaqon.aoc.aoc24.day13;

import java.util.Optional;

record Game(Vector buttonA, Vector buttonB, Vector prize) {
    public Optional<Long> minTokensToWin() {
        // xA * i + xB * j = xP  ->  (xP - xB * j) / xA = i
        // yA * i + yB * j = yP. ->  yA * (xP - xB * j) / xA + yB * j = yP
        // yA * (xP - xB * j) / xA + yB * j = yP
        // - yA * (xB * j) / xA + yB * j = yP - yA * xP / xA
        // j * (yB - yA * xB / xA) = yP - yA * xP / xA
        // j = (yP - yA * xP / xA) / ( yB - yA * (xB) / xA)

        if (buttonA.x() == 0) {
            double j = Math.floor(prize.x() / buttonB.x());
            if (j * buttonB.x() == prize.x() && j * buttonB.y() == prize.y()) {
                return Optional.of((long) j);
            }
            return Optional.empty();
        }
        if (buttonA.x() + buttonB.x() == 0) {
            return Optional.empty();
        }
        double j = Math.round((prize.y() - buttonA.y() * prize.x() / buttonA.x()) / (buttonB.y() - buttonA.y() * buttonB.x() / buttonA.x()));
        double i = Math.round((prize.x() - buttonB.x() * j) / buttonA.x());
        if (i * buttonA.x() + j * buttonB.x() == prize.x() && i * buttonA.y() + j * buttonB.y() == prize.y()) {
            return Optional.of((long) (i * 3 + j));
        }
        return Optional.empty();
    }
}

