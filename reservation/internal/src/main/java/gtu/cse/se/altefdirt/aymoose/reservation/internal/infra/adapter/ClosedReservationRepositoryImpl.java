package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.JpaClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper.ClosedReservationMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ClosedReservationRepositoryImpl implements ClosedReservationRepository {

    private final JpaClosedReservationRepository jpaRepository;
    private final ClosedReservationMapper mapper;

    @Override
    public ClosedReservation save(ClosedReservation reservation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(reservation)));
    }

    @Override
    public Optional<ClosedReservation> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public List<ClosedReservation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ClosedReservation> findByIds(List<AggregateId> ids) {
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
    public boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour) {
        return jpaRepository.isTimeSlotInUse(courtId.value(), date, hour);
    }

    @Override
    public List<ClosedReservation> findByCourtIdAndDate(AggregateId courtId, LocalDate date) {
        return jpaRepository.findByCourtIdAndDate(courtId.value(), date).stream().map(mapper::toDomain).toList();
    }
}
