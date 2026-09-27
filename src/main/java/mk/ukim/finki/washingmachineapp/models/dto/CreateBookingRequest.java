package mk.ukim.finki.washingmachineapp.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record CreateBookingRequest(
        @org.jetbrains.annotations.NotNull
        Long machineId,
        @NotNull
        Long studentId,
        @NotNull @Future
        LocalDateTime startTime
) {
}