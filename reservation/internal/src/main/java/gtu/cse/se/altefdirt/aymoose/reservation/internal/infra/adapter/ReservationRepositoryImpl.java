package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.JpaReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper.ReservationMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository jpaRepository;
    private final ReservationMapper mapper;

    @Override
    public Reservation save(Reservation reservation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(reservation)));
    }

    @Override
    public Optional<Reservation> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public List<Reservation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Reservation> findByIds(List<AggregateId> ids) {
        return jpaRepository.findAllById(ids.stream().map(AggregateId::value).toList()).stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(AggregateId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public int deleteById(AggregateId id) {
        jpaRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return jpaRepository.existsByIds(ids.stream().map(AggregateId::id).toList(), ids.size());
    }

    @Override
    public int countByPendingReservationsByUserId(AggregateId userId) {
        return jpaRepository.findByPendingReservationsByUserId(userId.value(), ReservationStatus.PENDING);
    }

    @Override
    public boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour) {
        return jpaRepository.isTimeSlotInUse(courtId.value(), date.localValue(), hour);
    }

    @Override
    public List<Reservation> findByCourtIdAndDate(AggregateId courtId, Date date) {
        log.debug("Finding reservations for court {} on date {}", courtId, date.localValue());
        return jpaRepository.findByCourtIdAndDate(courtId.value(), date.localValue()).stream().map(mapper::toDomain)
                .toList();
    }
}
