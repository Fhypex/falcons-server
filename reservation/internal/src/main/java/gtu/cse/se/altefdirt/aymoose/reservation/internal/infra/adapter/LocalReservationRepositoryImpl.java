package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.JpaLocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper.LocalReservationMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class LocalReservationRepositoryImpl implements LocalReservationRepository {

    private final JpaLocalReservationRepository jpaRepository;
    private final LocalReservationMapper mapper;

    @Override
    public LocalReservation save(LocalReservation reservation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(reservation)));
    }

    @Override
    public Optional<LocalReservation> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public List<LocalReservation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<LocalReservation> findByIds(List<AggregateId> ids) {
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
    public boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour) {
        return jpaRepository.isTimeSlotInUse(courtId.value(), date.localValue(), hour);
    }

    @Override
    public List<LocalReservation> findByCourtIdAndDate(AggregateId courtId, Date date) {
        return jpaRepository.findByCourtIdAndDate(courtId.value(), date.localValue()).stream().map(mapper::toDomain)
                .toList();
    }
}
