package ch.ebynaqon.aoc.aoc24.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record ProblemInput(GardenPlot[][] plots, int rows, int cols) {
    public List<GardenPlot> allPlots() {
        ArrayList<GardenPlot> result = new ArrayList<>(rows * cols);
        for (int row = 0; row < rows; row++) {
            result.addAll(Arrays.asList(plots[row]));
        }
        return result;
    }

    public boolean isWithinBounds(Position position) {
        return position.row() >= 0 && position.row() < rows && position.column() >= 0 && position.column() < cols;
    }

    public GardenPlot getPlot(Position position) {
        return plots[position.row()][position.column()];
    }
}

