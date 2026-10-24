package mk.ukim.finki.washingmachineapp.repository;

import mk.ukim.finki.washingmachineapp.models.domain.Booking;
import mk.ukim.finki.washingmachineapp.models.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.machine.id = :machineId
          AND b.status IN :activeStatuses
          AND b.startTime < :endTime
          AND b.endTime > :startTime
        """)
    boolean existsOverlappingBooking(
            @Param("machineId") Long machineId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("activeStatuses") List<BookingStatus> activeStatuses);

    @Query("""
        SELECT COUNT(b) FROM Booking b
        WHERE b.student.id = :studentId
          AND b.status IN :activeStatuses
          AND b.startTime >= :weekStart
          AND b.startTime < :weekEnd
        """)
    long countActiveBookingsInWeek(
            @Param("studentId") Long studentId,
            @Param("weekStart") LocalDateTime weekStart,
            @Param("weekEnd") LocalDateTime weekEnd,
            @Param("activeStatuses") List<BookingStatus> activeStatuses);

    List<Booking> findByStatusAndStartTimeBefore(BookingStatus status, LocalDateTime threshold);
}
