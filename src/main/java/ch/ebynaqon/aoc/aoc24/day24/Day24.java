package ch.ebynaqon.aoc.aoc24.day24;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

interface Day24 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String[] initialWiresAndGates = input.getWholeInput().split("\\n\\n");
        HashMap<String, WireState> initialWireStates = new HashMap<>();
        for (String initialWire : initialWiresAndGates[0].split("\\n")) {
            String[] nameAndState = initialWire.split(":\\s*");
            initialWireStates.put(nameAndState[0], WireState.from(nameAndState[1]));
        }
        List<Gate> gates = Arrays.stream(initialWiresAndGates[1].split("\\n"))
                .map(Day24::parseGate).toList();
        return new ProblemInput(initialWireStates, gates);
    }

    private static Gate parseGate(String input) {
        String[] parts = input.split("\\s+");
        return Gate.from(parts[1], parts[0], parts[2], parts[4]);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        HashMap<String, WireState> wireStates = new HashMap<>(problem.initialWireStates());
        Queue<Gate> gatesToCompute = new LinkedList<>(problem.gates());
        while (!gatesToCompute.isEmpty()) {
            Gate gate = gatesToCompute.poll();
            if (wireStates.containsKey(gate.input1()) && wireStates.containsKey(gate.input2())) {
                WireState output = gate.compute(wireStates.get(gate.input1()), wireStates.get(gate.input2()));
                wireStates.put(gate.output(), output);
            } else {
                gatesToCompute.add(gate);
            }
        }
        List<Integer> outputNumbers = wireStates.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .sorted(Map.Entry.<String, WireState>comparingByKey().reversed())
                .map(entry -> entry.getValue() == WireState.TRUE ? 1 : 0)
                .toList();
        long result = 0;
        for (Integer outputNumber : outputNumbers) {
            result = (result << 1) + outputNumber;
        }
        return result;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

record ProblemInput(Map<String, WireState> initialWireStates, List<Gate> gates) {
}

interface Gate {
    String input1();

    String input2();

    String output();

    WireState compute(WireState stateInput1, WireState stateInput2);

    static Gate from(String type, String input1, String input2, String output) {
        return switch (type) {
            case "AND" -> new AndGate(input1, input2, output);
            case "XOR" -> new XorGate(input1, input2, output);
            case "OR" -> new OrGate(input1, input2, output);
            default -> throw new IllegalArgumentException("Unknown gate type: " + type);
        };
    }
}

record AndGate(String input1, String input2, String output) implements Gate {
    @Override
    public WireState compute(WireState stateInput1, WireState stateInput2) {
        return stateInput1 == WireState.TRUE && stateInput2 == WireState.TRUE ? WireState.TRUE : WireState.FALSE;
    }
}

record XorGate(String input1, String input2, String output) implements Gate {
    @Override
    public WireState compute(WireState stateInput1, WireState stateInput2) {
        return stateInput1 != stateInput2 ? WireState.TRUE : WireState.FALSE;
    }
}

record OrGate(String input1, String input2, String output) implements Gate {
    @Override
    public WireState compute(WireState stateInput1, WireState stateInput2) {
        return stateInput1 == WireState.TRUE || stateInput2 == WireState.TRUE ? WireState.TRUE : WireState.FALSE;
    }
}

enum WireState {
    TRUE, FALSE, UNKNOWN;

    public static WireState from(String input) {
        return switch (input) {
            case "1" -> TRUE;
            case "0" -> FALSE;
            default -> throw new IllegalArgumentException("Unknown wire state: " + input);
        };
    }
}
