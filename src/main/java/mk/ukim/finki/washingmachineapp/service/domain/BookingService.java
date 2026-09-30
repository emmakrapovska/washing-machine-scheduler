package mk.ukim.finki.washingmachineapp.service.domain;

import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.domain.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<Booking> findById(Long id);

    List<Booking> findAll();

    Booking create(Machine machine, Student student, LocalDateTime startTime);

    Booking confirmAttendance(Long id);

    Booking cancel(Long id);

    Optional<Booking> deleteById(Long id);
}