package mk.ukim.finki.washingmachineapp.service.domain;

import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.domain.Student;
import mk.ukim.finki.washingmachineapp.models.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<Booking> findById(Long id);

    List<Booking> findAll();

    Booking create(Machine machine, Student student, LocalDateTime startTime);

    Optional<Booking> updateStatus(Long id, BookingStatus newStatus);

    Optional<Booking> deleteById(Long id);
}