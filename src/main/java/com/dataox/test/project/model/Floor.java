package com.dataox.test.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import lombok.Getter;

@Getter
public class Floor {
    private final int level;
    private boolean upCall;
    private boolean downCall;
    private final List<Passenger> passengers;

    public Floor(int level, List<Passenger> passengers) {
        this.level = level;
        this.passengers = passengers;
        updateButtonValues();
    }

    public Floor(int level) {
        this(level, new ArrayList<>());
        updateButtonValues();
    }

    public boolean isEmpty() {
        return passengers == null || passengers.isEmpty();
    }

    public boolean isUpDirectionPriority() {
        if (passengers.isEmpty()) {
            return false;
        } else {
            return (double) passengers.size() / 2 <= passengers.stream()
                    .mapToInt(Passenger::getTargetFloor)
                    .filter(el -> el > level)
                    .count();
        }
    }

    public void addPassengersOnFloor(List<Passenger> added) {
        this.passengers.addAll(added);
        updateButtonValues();
    }

    public List<Passenger> removePassengers(boolean isUpElevatorDirection, int quantity) {
        List<Passenger> removed = new ArrayList<>();
        ListIterator<Passenger> listIterator = passengers.listIterator();
        int counter = 0;
        while (listIterator.hasNext() && counter < quantity) {
            if (isUpElevatorDirection == listIterator.next().getTargetFloor() > level) {
                removed.add(listIterator.previous());
                listIterator.remove();
                counter++;
            }
        }
        updateButtonValues();
        return removed;
    }

    private void updateButtonValues() {
        if (passengers == null || passengers.isEmpty()) {
            upCall = false;
            downCall = false;
        } else {
            upCall = level < passengers.stream()
                    .mapToInt(Passenger::getTargetFloor)
                    .max()
                    .getAsInt();
            downCall = level > passengers.stream()
                    .mapToInt(Passenger::getTargetFloor)
                    .min()
                    .getAsInt();
        }
    }

    @Override
    public String toString() {
        return "Floor " + level +
                "\t U= " + upCall +
                "\t D= " + downCall +
                "\tp= " + passengers;
    }
}
