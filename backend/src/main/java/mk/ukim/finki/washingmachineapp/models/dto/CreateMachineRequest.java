package mk.ukim.finki.washingmachineapp.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.enums.MachineStatus;
import mk.ukim.finki.washingmachineapp.models.enums.MachineType;

public record CreateMachineRequest(
        @NotNull
        MachineType machineType,
        @NotBlank
        String location,
        @NotNull
        MachineStatus status
) {
    public Machine toMachine() {
        Machine machine = new Machine();
        machine.setMachineType(machineType());
        machine.setLocation(location());
        machine.setStatus(status());
        return machine;
    }
}