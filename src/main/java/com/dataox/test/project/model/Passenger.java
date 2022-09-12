package com.dataox.test.project.model;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Passenger {
    @Getter
    private int targetFloor;

    public int updateTargetFloor(int currentFloor, int maxFloor) {
        while ((targetFloor = new Random().nextInt(maxFloor)) == currentFloor);
        return targetFloor;
    }

    @Override
    public String toString() {
        return "" + targetFloor;
    }
}
