package mk.ukim.finki.washingmachineapp.service.application;

import mk.ukim.finki.washingmachineapp.models.dto.BookingResponse;
import mk.ukim.finki.washingmachineapp.models.dto.CreateBookingRequest;

import java.util.List;
import java.util.Optional;

public interface BookingApplicationService {

    Optional<BookingResponse> findById(Long id);

    List<BookingResponse> findAll();

    BookingResponse create(CreateBookingRequest request);

    BookingResponse confirmAttendance(Long id);

    BookingResponse cancel(Long id);

    Optional<BookingResponse> deleteById(Long id);
}