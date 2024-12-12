package ch.ebynaqon.aoc.aoc24.day12;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
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
        ArrayList<GardenRegion> regions = findGardenRegions(problem);
        return regions.stream().mapToInt(region -> region.area() * region.perimeter()).sum();
    }

    private static ArrayList<GardenRegion> findGardenRegions(ProblemInput problem) {
        ArrayList<GardenRegion> regions = new ArrayList<>();
        List<GardenPlot> allPlots = problem.allPlots();
        HashSet<GardenPlot> remainingPlots = new HashSet<>(allPlots);
        Queue<GardenPlot> plotsToCheck = new LinkedList<>();
        while (!remainingPlots.isEmpty()) {
            GardenPlot startingPlot = remainingPlots.stream().findFirst().get();
            char plant = startingPlot.plant();
            plotsToCheck.add(startingPlot);
            remainingPlots.remove(startingPlot);
            int area = 1;
            int perimeter = 0;
            List<Fence> fences = new ArrayList<>();
            while (!plotsToCheck.isEmpty()) {
                GardenPlot currentPlot = plotsToCheck.poll();
                for (Position neighbour : currentPlot.position().neighbours()) {
                    if (!problem.isWithinBounds(neighbour)) {
                        fences.add(new Fence(currentPlot.position(), neighbour));
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
                            fences.add(new Fence(currentPlot.position(), neighbour));
                            perimeter++;
                        }
                    }
                }
            }
            regions.add(new GardenRegion(area, perimeter, fences));
        }
        return regions;
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        ArrayList<GardenRegion> regions = findGardenRegions(problem);
        return regions.stream().mapToInt(region -> region.area() * region.numberOfSides()).sum();
    }
}

