package com.blackship.battlesheep.game.state.fleet;

import com.blackship.battlesheep.game.state.exceptions.ImpossibleShip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
        List<Integer> currentShip = null;
        while(currentShip == null) {
            currentShip = generateShip(4);
        }
        generatedShipPositions.add(currentShip);
        takenPositions.addAll(currentShip);
        //2x3 masts
        IntStream.rangeClosed(1, 2).forEach(i -> {
            List<Integer> ship = null;
            while(ship == null) {
                ship = generateShip(3);
            }
            generatedShipPositions.add(ship);
            takenPositions.addAll(ship);
        });
        //3x2 masts
        IntStream.rangeClosed(1, 3).forEach(i -> {
            List<Integer> ship = null;
            while(ship == null) {
                ship = generateShip(2);
            }
            generatedShipPositions.add(ship);
            takenPositions.addAll(ship);
        });
        //4x1 mast
        IntStream.rangeClosed(1, 4).forEach(i -> {
            List<Integer> ship = null;
            while(ship == null) {
                ship = generateShip(1);
            }
            generatedShipPositions.add(ship);
            takenPositions.addAll(ship);
        });

        return generatedShipPositions;
    }

    private List<Integer> generateShip(Integer mastCount) {
        List<Integer> positions = new ArrayList<>();
        Integer startingPosition = generateValidStartingPosition();
        positions.add(startingPosition);
        if(mastCount == 1) return positions;
        Integer nextPosition;
        try {
            nextPosition = generateValidNextPosition(startingPosition, positions);
        } catch (ImpossibleShip impossibleShip) {
            return null;
        }
        positions.add(nextPosition);
        if(mastCount == 2) return positions;
        try {
            nextPosition = generateValidNextPosition(nextPosition, positions);
        } catch (ImpossibleShip impossibleShip) {
            return null;
        }
        positions.add(nextPosition);
        if(mastCount == 3) return positions;
        try {
            nextPosition = generateValidNextPosition(nextPosition, positions);
        } catch (ImpossibleShip impossibleShip) {
            return null;
        }
        positions.add(nextPosition);

        return positions;
    }

    private Integer generateValidNextPosition(Integer startingPosition, List<Integer> positions) throws ImpossibleShip {
        Integer positionCandidate = 0;
        int attemptCount = 0;
        while(!isPositionValid(positionCandidate) || positions.contains(positionCandidate)) {
            if(attemptCount == 5) throw new ImpossibleShip();
            positionCandidate = generateNextPosition(startingPosition);
            attemptCount++;
        }

        return positionCandidate;
    }

    private Integer generateNextPosition(Integer startingPosition) {
        List<Integer> possiblePositions = Arrays.asList(-10, -1, 1, 10);
        //if position is at the right wall
        if(startingPosition % 10 == 0) possiblePositions = Arrays.asList(-10, -1, 10);
        //if position is at the left wall
        if(startingPosition % 10 == 1) possiblePositions = Arrays.asList(-10, 1, 10);

        Integer randomDirection = generateRandomNumber(possiblePositions.size() - 1);

        return startingPosition + possiblePositions.get(randomDirection);
    }

    private Integer generateValidStartingPosition() {
        Integer positionCandidate = 0;
        while(!isPositionValid(positionCandidate)) {
            positionCandidate = generateRandomNumber(100);
        }

        return positionCandidate;
    }

    private boolean isPositionValid(Integer positionCandidate) {
        if(positionCandidate == 0) return false;
        if(!doesBelongToBoard(positionCandidate)) return false;
        if(takenPositions.contains(positionCandidate)) return false;

        return !hasNeighbours(positionCandidate);
    }

    private boolean hasNeighbours(Integer positionCandidate) {
        List<Integer> possibleNeighbours = Arrays.asList(-11, -10, -9, -1, 1, 9, 10, 11);

        for (Integer neighbourPosition : possibleNeighbours) {
            if(takenPositions.contains(neighbourPosition + positionCandidate)) return true;
        }

        return false;
    }

    private boolean doesBelongToBoard(int position) {
        return position <= 100 && position >=1;
    }

    private Integer generateRandomNumber(Integer bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }
}
