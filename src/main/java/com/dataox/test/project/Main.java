package com.dataox.test.project;

import com.dataox.test.project.model.Building;
import com.dataox.test.project.model.Elevator;
import com.dataox.test.project.service.InitialConditionsService;
import com.dataox.test.project.service.PassengerTransferService;

public class Main {
    public static void main(String[] args) {
        Building building = InitialConditionsService.getBuilding();
        PassengerTransferService service = new PassengerTransferService(building, new Elevator());

        while (true) {
            service.doWork();
        }
    }
}
