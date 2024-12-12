package ch.ebynaqon.aoc.aoc24.day12;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record GardenRegion(int area, int perimeter, List<Fence> fences) {
    private static int countHorizontal(List<Fence> fences) {
        int sides = 0;
        Map<Integer, List<Fence>> fencesByRow = fences.stream().collect(Collectors.groupingBy(fence -> fence.inside().row()));
        for (List<Fence> fencesInRow : fencesByRow.values()) {
            List<Fence> sortedByColumn = fencesInRow.stream().sorted(Comparator.comparing(fence -> fence.inside().column())).toList();
            int lastColumn = sortedByColumn.getFirst().inside().column();
            sides++;
            for (int i = 1; i < sortedByColumn.size(); i++) {
                int column = sortedByColumn.get(i).inside().column();
                if (column > lastColumn + 1) {
                    sides++;
                }
                lastColumn = column;
            }
        }
        return sides;
    }

    private static int countVertical(List<Fence> fences) {
        int sides = 0;
        Map<Integer, List<Fence>> fencesByColumn = fences.stream().collect(Collectors.groupingBy(fence -> fence.inside().column()));
        for (List<Fence> fencesInRow : fencesByColumn.values()) {
            List<Fence> sortedByRow = fencesInRow.stream().sorted(Comparator.comparing(fence -> fence.inside().row())).toList();
            int lastRow = sortedByRow.getFirst().inside().row();
            sides++;
            for (int i = 1; i < sortedByRow.size(); i++) {
                int row = sortedByRow.get(i).inside().row();
                if (row > lastRow + 1) {
                    sides++;
                }
                lastRow = row;
            }
        }
        return sides;
    }

    public int numberOfSides() {
        int sides = 0;
        List<Fence> topFences = fences.stream().filter(fence -> fence.inside().row() == fence.outside().row() - 1).toList();
        List<Fence> bottomFences = fences.stream().filter(fence -> fence.inside().row() == fence.outside().row() + 1).toList();
        List<Fence> leftFences = fences.stream().filter(fence -> fence.inside().column() == fence.outside().column() - 1).toList();
        List<Fence> rightFences = fences.stream().filter(fence -> fence.inside().column() == fence.outside().column() + 1).toList();
        sides += countHorizontal(topFences);
        sides += countHorizontal(bottomFences);
        sides += countVertical(leftFences);
        sides += countVertical(rightFences);
        return sides;
    }
}

record Fence(Position inside, Position outside) {
}
