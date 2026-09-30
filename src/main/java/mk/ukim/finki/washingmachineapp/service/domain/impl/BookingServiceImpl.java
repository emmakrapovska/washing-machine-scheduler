package mk.ukim.finki.washingmachineapp.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.domain.Student;
import mk.ukim.finki.washingmachineapp.models.enums.BookingStatus;
import mk.ukim.finki.washingmachineapp.models.enums.MachineStatus;
import mk.ukim.finki.washingmachineapp.models.exception.*;
import mk.ukim.finki.washingmachineapp.repository.BookingRepository;
import mk.ukim.finki.washingmachineapp.service.domain.BookingService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private static final int MAX_BOOKINGS_PER_WEEK = 2;
    private static final int SLOT_DURATION_HOURS = 1;
    private static final int MINUTES_TO_CONFIRM = 15;
    private static final List<BookingStatus> ACTIVE_STATUSES = List.of(BookingStatus.CREATED, BookingStatus.APPROVED);
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional
    public Booking create(Machine machine, Student student, LocalDateTime startTime) {
        if (machine.getStatus() == MachineStatus.NEISPRAVNA) {
            throw new MachineNotOperationalException(machine.getId());
        }

        LocalDateTime endTime = startTime.plusHours(SLOT_DURATION_HOURS);

        LocalDateTime weekStart = startTime
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .toLocalDate()
                .atStartOfDay();
        LocalDateTime weekEnd = weekStart.plusWeeks(1);

        long activeBookingsThisWeek = bookingRepository.countActiveBookingsInWeek(
                student.getId(), weekStart, weekEnd, ACTIVE_STATUSES);
        if (activeBookingsThisWeek >= MAX_BOOKINGS_PER_WEEK) {
            throw new WeeklyBookingLimitExceededException(student.getId(), MAX_BOOKINGS_PER_WEEK);
        }

        boolean overlaps = bookingRepository.existsOverlappingBooking(
                machine.getId(), startTime, endTime, ACTIVE_STATUSES);
        if (overlaps) {
            throw new BookingOverlapException(machine.getId());
        }

        Booking booking = new Booking();
        booking.setMachine(machine);
        booking.setStudent(student);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setStatus(BookingStatus.CREATED);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking confirmAttendance(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        LocalDateTime confirmDeadline = booking.getStartTime().plusMinutes(MINUTES_TO_CONFIRM);
        if (LocalDateTime.now().isAfter(confirmDeadline)) {
            throw new ConfirmationDeadlinePassedException(booking.getId());
        }

        booking.setStatus(BookingStatus.APPROVED);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        booking.setStatus(BookingStatus.CANCELED);
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> deleteById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.ifPresent(bookingRepository::delete);
        return booking;
    }
}