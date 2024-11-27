package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.CourtEntity;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.JpaCourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class CourtRepositryImpl implements CourtRepository {

    private final JpaCourtRepository jpaCourtRepository;
    private final CourtFactory courtFactory;

    private Court build(CourtEntity courtEntity) {
        return courtFactory.load(AggregateId.from(courtEntity.getId()), 
                                 AggregateId.from(courtEntity.getFacilityId()), 
                                 new CourtDetails(courtEntity.getName(), courtEntity.getDescription()),
                                 new Measurements(courtEntity.getHeight(), courtEntity.getWidth()),
                                 new Capacity(courtEntity.getCapacity())
                              );
    }

    @Override
    public Court save(Court court) {
        CourtEntity courtEntity = jpaCourtRepository.save(CourtEntity.from(court));
        return build(courtEntity);
    }


    @Override
    public Optional<Court> findById(AggregateId id) {
        CourtEntity courtEntity = jpaCourtRepository.findById(id.value()).get();
        return Optional.of(build(courtEntity));
    }

    @Override
    public List<Court> findAll() {
        return jpaCourtRepository.findAll().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Court> findByFacilityId(AggregateId facilityId) {
        return jpaCourtRepository.findAllByFacilityId(facilityId.value()).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public int deleteById(AggregateId id) {
        jpaCourtRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public int deleteByFacilityId(AggregateId facilityId) {
        jpaCourtRepository.deleteByFacilityId(facilityId.value());
        return 1;
    }

    @Override
    public List<Court> findByIds(List<AggregateId> ids) {
        return jpaCourtRepository.findAllById(ids.stream().map(AggregateId::value).collect(Collectors.toList())).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existsById(AggregateId id) {
        return jpaCourtRepository.existsById(id.value());
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return jpaCourtRepository.existsByIds(ids.stream().map(AggregateId::value).collect(Collectors.toList()), ids.size());
    }
}