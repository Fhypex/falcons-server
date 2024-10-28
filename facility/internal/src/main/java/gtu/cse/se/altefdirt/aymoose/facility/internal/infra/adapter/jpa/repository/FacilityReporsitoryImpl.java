package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.FacilityEntity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaFacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class FacilityRepositoryImpl implements FacilityRepository {

    private final JpaFacilityRepository jpaFacilityRepository;
    private final FacilityFactory facilityFactory;

    private Facility build(FacilityEntity facilityEntity) {
        return facilityFactory.load(AggregateId.from(facilityEntity.getId()), 
                                    AggregateId.from(facilityEntity.getUserId()), 
                                    facilityEntity.getFacilityName(),
                                    facilityEntity.getPhoneNumber(),
                                    facilityEntity.getFacilityDescription(),
                                    facilityEntity.getLocation(),
                                    facilityEntity.getContactDetails(),
                                    new FacilityCapacity(facilityEntity.getCourtCount()),
                                    facilityEntity.isActive());
    }

    @Override
    public Facility save(Facility facility) {
        FacilityEntity facilityEntity = jpaFacilityRepository.save(FacilityEntity.from(facility));
        return build(facilityEntity);
    }

    @Override
    public Optional<Facility> findById(AggregateId id) {
        return jpaFacilityRepository.findById(id.value()).map(this::build);
    }

    @Override
    public List<Facility> findAll() {
        return jpaFacilityRepository.findAll().stream()
                                     .map(this::build)
                                     .collect(Collectors.toList());
    }
}

