package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(AggregateId id) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findByIds(List<AggregateId> ids) {
        return null;
    }

    @Override
    public boolean existsById(AggregateId id) {
        return true;
    }

    @Override
    public int deleteById(AggregateId id) {
        return 0;
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return true;
    }

    @Override
    public int countByPendingReservationsByUserId(AggregateId userId) {
        return 0;
    }

    @Override
    public boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour) {
        return false;
    }
}
