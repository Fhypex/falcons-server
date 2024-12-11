package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaCourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper.CourtMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class CourtRepositryImpl implements CourtRepository {

    private final JpaCourtRepository courtRepository;
    private final CourtMapper mapper;

    @Override
    public Court save(Court court) {
        return mapper.toDomain(courtRepository.save(mapper.toEntity(court)));
    }

    @Override
    public Optional<Court> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(courtRepository.findById(id.value()).get()));
    }

    @Override
    public List<Court> findAll() {
        return courtRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Court> findByFacilityId(AggregateId facilityId) {
        return courtRepository.findAllByFacilityId(facilityId.value()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public int deleteById(AggregateId id) {
        courtRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public int deleteByFacilityId(AggregateId facilityId) {
        courtRepository.deleteByFacilityId(facilityId.value());
        return 1;
    }

    @Override
    public List<Court> findByIds(List<AggregateId> ids) {
        return courtRepository.findAllById(ids.stream().map(AggregateId::value).toList())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(AggregateId id) {
        return courtRepository.existsById(id.value());
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return courtRepository.existsByIds(ids.stream().map(AggregateId::value).toList(), ids.size());
    }

    @Override
    public boolean existsByIdAndOwnerId(AggregateId id, AggregateId userId) {
        return courtRepository.existsByIdAndOwnerId(id.value(), userId.value());
    }
}