package mk.ukim.finki.washingmachineapp.models.dto;

import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BookingResponse(
        Long id,
        Long machineId,
        String machineLocation,
        Long studentId,
        String studentName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BookingStatus status
) {
    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getMachine().getId(),
                booking.getMachine().getLocation(),
                booking.getStudent().getId(),
                booking.getStudent().getName(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus()
        );
    }
}