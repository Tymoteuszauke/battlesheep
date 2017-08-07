package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.exceptions.ImpossibleShip;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 02.08.2017
 */
public class FleetGenerator {

    private List<Integer> takenPositions;

    public static List<List<Integer>> hardcodeShips() {
        List<List<Integer>> ships = new ArrayList<>();
        ships.add(Arrays.asList(12, 22, 32, 42));
        ships.add(Arrays.asList(14, 15, 16));
        ships.add(Arrays.asList(37, 47, 57));
        ships.add(Arrays.asList(72, 73));
        ships.add(Arrays.asList(76, 77));
        ships.add(Arrays.asList(99, 100));
        ships.add(Arrays.asList(19));
        ships.add(Arrays.asList(54));
        ships.add(Arrays.asList(80));
        ships.add(Arrays.asList(94));

        return ships;
    }

    public List<List<Integer>> generateRandomFleet() {
        List<List<Integer>> generatedShipPositions = new ArrayList<>();
        takenPositions = new ArrayList<>();

        //4 mast
        generateShipsOfOneType(generatedShipPositions, 1, 4);
        //2x3 masts
        generateShipsOfOneType(generatedShipPositions, 2, 3);
        //3x2 masts
        generateShipsOfOneType(generatedShipPositions, 3, 2);
        //4x1 mast
        generateShipsOfOneType(generatedShipPositions, 4, 1);

        return generatedShipPositions;
    }

    private void generateShipsOfOneType(List<List<Integer>> generatedShipPositions, Integer shipCount, Integer mastCount) {
        IntStream.rangeClosed(1, shipCount).forEach(i -> {
            List<Integer> ship = Collections.emptyList();
            while (ship.isEmpty()) {
                ship = generateShip(mastCount);
            }
            generatedShipPositions.add(ship);
            takenPositions.addAll(ship);
        });
    }

    private List<Integer> generateShip(Integer mastCount) {
        List<Integer> positions = new ArrayList<>();
        Integer startingPosition = generateValidStartingPosition();

        positions.add(startingPosition);
        if (mastCount == 1) return positions;
        Integer nextPosition;

        try {
            nextPosition = generateValidNextPosition(startingPosition, positions);
            positions.add(nextPosition);
            if (mastCount == 2) return positions;
            nextPosition = generateValidNextPosition(nextPosition, positions);
            positions.add(nextPosition);
            if (mastCount == 3) return positions;
            nextPosition = generateValidNextPosition(nextPosition, positions);
            positions.add(nextPosition);
        } catch (ImpossibleShip impossibleShip) {
            return Collections.emptyList();
        }

        return positions;
    }

    private Integer generateValidNextPosition(Integer startingPosition, List<Integer> positions) throws ImpossibleShip {
        Integer positionCandidate = 0;
        int attemptCount = 0;
        while (!isPositionValid(positionCandidate) || positions.contains(positionCandidate)) {
            if (attemptCount == 5) throw new ImpossibleShip();
            positionCandidate = generateNextPosition(startingPosition);
            attemptCount++;
        }

        return positionCandidate;
    }

    private Integer generateNextPosition(Integer startingPosition) {
        List<Integer> possiblePositions = Arrays.asList(-10, -1, 1, 10);
        //if position is at the right wall
        if (startingPosition % 10 == 0) possiblePositions = Arrays.asList(-10, -1, 10);
        //if position is at the left wall
        if (startingPosition % 10 == 1) possiblePositions = Arrays.asList(-10, 1, 10);

        Integer randomDirection = generateRandomNumber(possiblePositions.size() - 1);

        return startingPosition + possiblePositions.get(randomDirection);
    }

    private Integer generateValidStartingPosition() {
        Integer positionCandidate = 0;
        while (!isPositionValid(positionCandidate)) {
            positionCandidate = generateRandomNumber(100);
        }

        return positionCandidate;
    }

    private boolean isPositionValid(Integer positionCandidate) {
        if (positionCandidate == 0) return false;
        if (!doesBelongToBoard(positionCandidate)) return false;
        if (takenPositions.contains(positionCandidate)) return false;

        return !hasNeighbours(positionCandidate);
    }

    private boolean hasNeighbours(Integer positionCandidate) {
        List<Integer> possibleNeighbours = Arrays.asList(-11, -10, -9, -1, 1, 9, 10, 11);

        for (Integer neighbourPosition : possibleNeighbours) {
            if (takenPositions.contains(neighbourPosition + positionCandidate)) return true;
        }

        return false;
    }

    private boolean doesBelongToBoard(int position) {
        return position <= 100 && position >= 1;
    }

    private Integer generateRandomNumber(Integer bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }
}
