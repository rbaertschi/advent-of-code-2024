package ch.ebynaqon.aoc.aoc24.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

record OutputDevice(List<Integer> outputs) {
    OutputDevice() {
        this(new ArrayList<>());
    }

    void write(int value) {
        outputs.add(value);
    }

    String output() {
        return outputs.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
