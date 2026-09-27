package mk.ukim.finki.washingmachineapp.models.dto;

import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.enums.MachineStatus;
import mk.ukim.finki.washingmachineapp.models.enums.MachineType;

public record MachineResponse(
        Long id,
        MachineType machineType,
        String location,
        MachineStatus status
) {
    public static MachineResponse from(Machine machine) {
        return new MachineResponse(
                machine.getId(),
                machine.getMachineType(),
                machine.getLocation(),
                machine.getStatus()
        );
    }
}