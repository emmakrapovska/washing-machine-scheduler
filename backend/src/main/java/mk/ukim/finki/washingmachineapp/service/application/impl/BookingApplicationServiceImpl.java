package mk.ukim.finki.washingmachineapp.service.application.impl;

import mk.ukim.finki.washingmachineapp.models.dto.BookingResponse;
import mk.ukim.finki.washingmachineapp.models.dto.CreateBookingRequest;
import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.domain.Student;
import mk.ukim.finki.washingmachineapp.models.exception.MachineNotFoundException;
import mk.ukim.finki.washingmachineapp.models.exception.StudentNotFoundException;
import mk.ukim.finki.washingmachineapp.repository.MachineRepository;
import mk.ukim.finki.washingmachineapp.repository.StudentRepository;
import mk.ukim.finki.washingmachineapp.service.application.BookingApplicationService;
import mk.ukim.finki.washingmachineapp.service.domain.BookingService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {

    private final BookingService bookingService;
    private final MachineRepository machineRepository;
    private final StudentRepository studentRepository;

    public BookingApplicationServiceImpl(BookingService bookingService, MachineRepository machineRepository,
                                         StudentRepository studentRepository) {
        this.bookingService = bookingService;
        this.machineRepository = machineRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<BookingResponse> findById(Long id) {
        return bookingService.findById(id)
                .map(BookingResponse::from);
    }

    @Override
    public List<BookingResponse> findAll() {
        return bookingService.findAll().stream()
                .map(BookingResponse::from).toList();
    }

    @Override
    public BookingResponse create(CreateBookingRequest request) {
        Machine machine = machineRepository.findById(request.machineId())
                .orElseThrow(() -> new MachineNotFoundException(request.machineId()));

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new StudentNotFoundException(request.studentId()));

        Booking booking = bookingService.create(machine, student, request.startTime());

        return BookingResponse.from(booking);
    }

    @Override
    public BookingResponse confirmAttendance(Long id) {
        Booking booking = bookingService.confirmAttendance(id);
        return BookingResponse.from(booking);
    }

    @Override
    public BookingResponse cancel(Long id) {
        Booking booking = bookingService.cancel(id);
        return BookingResponse.from(booking);
    }

    @Override
    public Optional<BookingResponse> deleteById(Long id) {
        return bookingService.deleteById(id)
                .map(BookingResponse::from);
    }
}