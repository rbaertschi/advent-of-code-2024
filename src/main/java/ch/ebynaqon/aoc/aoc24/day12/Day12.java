package ch.ebynaqon.aoc.aoc24.day12;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface Day12 {

    static ProblemInput parseProblem(RawProblemInput input) {
        List<String> lines = input.getLines();
        int rows = lines.size();
        int cols = lines.getFirst().length();
        GardenPlot[][] plots = new GardenPlot[rows][cols];
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < cols; col++) {
                char plant = line.charAt(col);
                plots[row][col] = new GardenPlot(plant, new Position(row, col));
            }
        }
        return new ProblemInput(plots, rows, cols);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<GardenPlot> allPlots = problem.allPlots();
        HashSet<GardenPlot> remainingPlots = new HashSet<>(allPlots);
        Queue<GardenPlot> plotsToCheck = new LinkedList<>();
        int result = 0;
        while (!remainingPlots.isEmpty()) {
            GardenPlot startingPlot = remainingPlots.stream().findFirst().get();
            char plant = startingPlot.plant();
            plotsToCheck.add(startingPlot);
            remainingPlots.remove(startingPlot);
            int area = 1;
            int perimeter = 0;
            while (!plotsToCheck.isEmpty()) {
                GardenPlot currentPlot = plotsToCheck.poll();
                for (Position neighbour : currentPlot.position().neighbours()) {
                    if (!problem.isWithinBounds(neighbour)) {
                        perimeter++;
                    } else {
                        GardenPlot neighbourPlot = problem.getPlot(neighbour);
                        if (neighbourPlot.plant() == plant) {
                            if (remainingPlots.contains(neighbourPlot)) {
                                area++;
                                plotsToCheck.add(neighbourPlot);
                                remainingPlots.remove(neighbourPlot);
                            }
                        } else {
                            perimeter++;
                        }
                    }
                }
            }
            result += area * perimeter;
        }
        return result;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        return 0;
    }
}

