package com.dataox.test.project.service;

import com.dataox.test.project.model.Building;
import com.dataox.test.project.model.Floor;
import com.dataox.test.project.model.Passenger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitialConditionsService {
    private static final int MAX_NUMBER_FLOORS = 5;
    private static final int MIN_NUMBER_FLOORS = 20;
    private static final int MAX_NUMBER_PASSENGERS_ON_FLOOR_AT_START = 10;

    public static Building getBuilding() {
        Building building = new Building(new Random().nextInt(MIN_NUMBER_FLOORS, MAX_NUMBER_FLOORS + 1),
                new HashMap<>());
        addFloorToBuilding(building.getNumberFloors(), building.getFloors());
        return building;
    }

    private static void addFloorToBuilding(int numberFloors, Map<Integer, Floor> floors) {
        for (int i = 0; i < numberFloors; i++) {
            floors.put(i, new Floor(i, initPassengersList(i, numberFloors)));
        }
    }

    private static List<Passenger> initPassengersList(int currentFloor, int numberFloors) {
        int numberPassengerOnFloor = new Random().nextInt(MAX_NUMBER_PASSENGERS_ON_FLOOR_AT_START); // from 0 to 9
        if (numberPassengerOnFloor == 0) {
            return new ArrayList<>();
        }
        return IntStream.rangeClosed(0, numberPassengerOnFloor)
                .mapToObj(i -> new Passenger())
                .peek(p -> p.updateTargetFloor(currentFloor, numberFloors))
                .collect(Collectors.toList());
    }
}
