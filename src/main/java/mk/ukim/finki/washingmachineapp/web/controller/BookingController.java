package mk.ukim.finki.washingmachineapp.web.controller;


import jakarta.validation.Valid;
import mk.ukim.finki.washingmachineapp.models.dto.BookingResponse;
import mk.ukim.finki.washingmachineapp.models.dto.CreateBookingRequest;
import mk.ukim.finki.washingmachineapp.models.enums.BookingStatus;
import mk.ukim.finki.washingmachineapp.models.exception.BookingNotFoundException;
import mk.ukim.finki.washingmachineapp.service.application.BookingApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingApplicationService bookingApplicationService;

    public BookingController(BookingApplicationService bookingApplicationService) {
        this.bookingApplicationService = bookingApplicationService;
    }

    @GetMapping
    public List<BookingResponse> findAll() {
        return bookingApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable Long id) {
        return bookingApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(@Valid @RequestBody CreateBookingRequest request) {
        return bookingApplicationService.create(request);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateStatus(@PathVariable Long id,
                                                        @RequestParam BookingStatus newStatus) {
        return bookingApplicationService.updateStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> deleteById(@PathVariable Long id) {
        return bookingApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
}