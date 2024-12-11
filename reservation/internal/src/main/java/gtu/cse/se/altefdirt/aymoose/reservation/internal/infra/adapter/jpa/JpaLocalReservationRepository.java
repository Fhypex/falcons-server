package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaLocalReservationRepository extends JpaRepository<LocalReservationEntity, UUID> {

    List<LocalReservationEntity> findAllByCourtId(UUID courtId);

    @Query("SELECT COUNT(a) = :size FROM LocalReservationEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);

    @Query("SELECT COUNT(a) > 0 FROM LocalReservationEntity a WHERE a.courtId = :courtId AND a.date = :date AND a.hour = :hour")
    boolean isTimeSlotInUse(UUID courtId, LocalDate date, int hour);

    List<LocalReservationEntity> findByCourtIdAndDate(UUID courtId, LocalDate date);
}
