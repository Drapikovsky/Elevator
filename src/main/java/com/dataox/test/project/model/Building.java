package com.dataox.test.project.model;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Building {
    private final int numberFloors;
    private final Map<Integer, Floor> floors;

    @Override
    public String toString() {
        String floorsToString = floors.entrySet().stream()
                .map(entry -> "\t" + entry.getKey() + " = " + entry.getValue() + "\n")
                .collect(Collectors.joining());
        return "Building{"
                + "numberFloors=" + numberFloors + ",\n"
                + "floors=\n" + floorsToString
                + "}";
    }
}
