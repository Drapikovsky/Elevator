package com.dataox.test.project.service;

import com.dataox.test.project.model.Building;
import com.dataox.test.project.model.Elevator;
import com.dataox.test.project.model.Floor;

import com.dataox.test.project.model.Passenger;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PassengerTransferService {
    private Building building;
    private Elevator elevator;
    private int currentLevel;
    private List<Passenger> removedFromElevator;

    public PassengerTransferService(Building building, Elevator elevator) {
        this.building = building;
        this.elevator = elevator;
        currentLevel = elevator.getPosition();
        removedFromElevator = new ArrayList<>();
    }

    public void doWork() {
        currentLevel = elevator.getPosition();

        System.out.println("------------------------------------------------------------------------------");
        System.out.println(elevator);
        System.out.println(building);
        System.out.println("------------------------------------------------------------------------------");

        if (elevator.isEmpty() && getCurrentFloor().isEmpty()) {
            elevator.setUpDirection((building.getNumberFloors() - 1 - currentLevel) > currentLevel);
            if (elevator.isUpDirection()) {
                elevator.setPosition(building.getFloors().values().stream()
                        .filter(Floor::isUpCall)
                        .mapToInt(Floor::getLevel)
                        .min()
                        .getAsInt());
            } else {
                elevator.setPosition(building.getFloors().values().stream()
                        .filter(Floor::isDownCall)
                        .mapToInt(Floor::getLevel)
                        .max()
                        .getAsInt());
            }
            return;
        }

        removedFromElevator = elevator.removePassengers();
        if (elevator.isEmpty()) {
            elevator.setUpDirection(getCurrentFloor().isUpDirectionPriority());
        }
        elevator.addPassengers(getCurrentFloor().removePassengers(elevator.isUpDirection(), elevator.getNumberOfAvailablePlaces()));
        removedFromElevator.forEach(passenger -> passenger.updateTargetFloor(currentLevel, building.getNumberFloors()));
        getCurrentFloor().addPassengersOnFloor(removedFromElevator);

        if (elevator.getPosition() == building.getNumberFloors() - 1) {
            elevator.setUpDirection(false);
        }
        if (elevator.getPosition() == 0) {
            elevator.setUpDirection(true);
        }

        elevator.setPosition(elevator.isUpDirection()
                ? elevator.getPosition() + 1
                : elevator.getPosition() - 1);
    }

    private Floor getCurrentFloor() {
        return building.getFloors().get(currentLevel);
    }
}
