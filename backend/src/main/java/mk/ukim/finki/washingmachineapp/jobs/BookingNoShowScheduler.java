package mk.ukim.finki.washingmachineapp.jobs;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.service.domain.BookingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BookingNoShowScheduler {

    private final BookingService bookingService;

    public BookingNoShowScheduler(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void cancelNoShowBookings() {
        List<Booking> canceled = bookingService.cancelNoShowBookings();

        if (!canceled.isEmpty()) {
            log.info("Avtomatski otkazhani {} rezervacii poradi nepotvrdeno", canceled.size());
        }
    }
}