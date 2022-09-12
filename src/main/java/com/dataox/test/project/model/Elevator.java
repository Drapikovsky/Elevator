package com.dataox.test.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Elevator {
    private static final int MAX_PASSENGER_ON_BOARD = 5;
    private List<Passenger> passengers = new ArrayList<>();
    @Setter
    private int position = 0;
    @Setter
    private boolean upDirection = true;

    public boolean isEmpty() {
        return passengers.isEmpty();
    }

    public int getNumberOfAvailablePlaces() {
        return MAX_PASSENGER_ON_BOARD - passengers.size();
    }

    public void addPassengers(List<Passenger> added) {
        this.passengers.addAll(added);
    }

    public List<Passenger> removePassengers() {
        List<Passenger> removed = new ArrayList<>();

        ListIterator<Passenger> listIterator = passengers.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().getTargetFloor() == position) {
                removed.add(listIterator.previous());
                listIterator.remove();
            }
        }
        return removed;
    }


    @Override
    public String toString() {
        return "Elevator{" +
                "pos=" + position +
                ", upDir=" + upDirection +
                ", passengers=" + passengers +
                '}';
    }
}
