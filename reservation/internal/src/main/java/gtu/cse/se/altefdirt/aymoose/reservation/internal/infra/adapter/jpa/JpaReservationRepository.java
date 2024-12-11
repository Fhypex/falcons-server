package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;

public interface JpaReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findAllByCourtId(UUID courtId);

    @Query("SELECT COUNT(a) = :size FROM ReservationEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);

    @Query("SELECT COUNT(a) > 0 FROM ReservationEntity a WHERE a.userId = :userId AND a.status = :status")
    List<ReservationEntity> findByPendingReservationsByUserId(UUID userId, ReservationStatus status);

    @Query("SELECT COUNT(a) > 0 FROM ReservationEntity a WHERE a.courtId = :courtId AND a.date = :date AND a.hour = :hour")
    boolean isTimeSlotInUse(UUID courtId, LocalDate date, int hour);

    List<ReservationEntity> findByCourtIdAndDate(UUID courtId, LocalDate date);
}
