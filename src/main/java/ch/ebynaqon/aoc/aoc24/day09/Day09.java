package ch.ebynaqon.aoc.aoc24.day09;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.List;

interface Day09 {

    static ProblemInput parseProblem(RawProblemInput input) {
        String rawInput = input.getWholeInput();
        ArrayList<FreeSpace> freeSpaces = new ArrayList<>(rawInput.length() / 2);
        ArrayList<Block> blocks = new ArrayList<>(rawInput.length() / 2);
        int currentPosition = 0;
        int currentId = 0;
        for (int i = 0; i < rawInput.length(); i++) {
            int currentLength = (rawInput.charAt(i) - '0');
            if (i % 2 == 0) {
                blocks.add(new Block(currentPosition, currentLength, currentId++));
            } else {
                freeSpaces.add(new FreeSpace(currentPosition, currentLength));
            }
            currentPosition += currentLength;
        }
        return new ProblemInput(blocks, freeSpaces);
    }

    static long solvePart1(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        ArrayList<Block> compacted = new ArrayList<>(problem.blocks().size());
        int currentBlockIndex = 0;
        int currentFreeSpaceIndex = 0;
        int currentFreeSpace = problem.freeSpaces().get(currentFreeSpaceIndex).length();
        int currentBlockToPlaceIndex = problem.blocks().size() - 1;
        int currentPosition = 0;
        compacted.add(problem.blocks().get(currentBlockIndex));
        currentPosition += problem.blocks().get(currentBlockIndex).length();
        while (currentBlockIndex < currentBlockToPlaceIndex) {
            Block currentBlockToPlace = problem.blocks().get(currentBlockToPlaceIndex);
            int currentId = currentBlockToPlace.id();
            int remaining = currentBlockToPlace.length();
            while (remaining >= currentFreeSpace) {
                remaining -= currentFreeSpace;
                compacted.add(new Block(currentPosition, currentFreeSpace, currentId));
                currentPosition += currentFreeSpace;
                currentFreeSpaceIndex++;
                currentFreeSpace = problem.freeSpaces().get(currentFreeSpaceIndex).length();
                currentBlockIndex++;
                if (currentBlockIndex == currentBlockToPlaceIndex) {
                    break;
                }
                compacted.add(problem.blocks().get(currentBlockIndex));
                currentPosition += problem.blocks().get(currentBlockIndex).length();
            }
            if (remaining > 0) {
                compacted.add(new Block(currentPosition, remaining, currentId));
                currentPosition += remaining;
                currentFreeSpace -= remaining;
            }
            currentBlockToPlaceIndex--;
        }
        return compacted.stream().mapToLong(Block::checksum).sum();
    }

    static long solvePart2(RawProblemInput input) {
        ProblemInput problem = parseProblem(input);
        List<Block> blocks = problem.blocks();
        List<FreeSpace> freeSpaces = problem.freeSpaces();
        ArrayList<Block> compacted = new ArrayList<>(blocks.size());
        for (int currentBlockIndex = blocks.size() - 1; currentBlockIndex >= 0; currentBlockIndex--) {
            boolean moved = false;
            Block currentBlock = blocks.get(currentBlockIndex);
            for (int freeSpaceIndex = 0; freeSpaceIndex < freeSpaces.size() && !moved; freeSpaceIndex++) {
                FreeSpace freeSpace = freeSpaces.get(freeSpaceIndex);
                if (freeSpace.length() >= currentBlock.length() && freeSpace.start() < currentBlock.start()) {
                    compacted.add(new Block(freeSpace.start(), currentBlock.length(), currentBlock.id()));
                    FreeSpace updatedFreeSpace = new FreeSpace(freeSpace.start() + currentBlock.length(), freeSpace.length() - currentBlock.length());
                    freeSpaces.set(freeSpaceIndex, updatedFreeSpace);
                    moved = true;
                }
            }
            if (!moved) {
                compacted.add(currentBlock);
            }
        }
        return compacted.stream().mapToLong(Block::checksum).sum();
    }
}
