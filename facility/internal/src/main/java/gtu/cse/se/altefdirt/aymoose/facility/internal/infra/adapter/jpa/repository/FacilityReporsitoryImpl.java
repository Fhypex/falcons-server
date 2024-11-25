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
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class FacilityRepositoryImpl implements FacilityRepository {

    private final JpaFacilityRepository jpaFacilityRepository;
    private final FacilityFactory factory;

    private Facility build(FacilityEntity entity) {
        return factory.load(
                AggregateId.from(entity.getId()),
                AggregateId.from(entity.getOwnerId()),
                new PhoneNumber(entity.getPhoneNumber()),
                entity.getName(),
                entity.getDescription(),
                new Address(entity.getCityId(), entity.getDistrictId(), entity.getFullAddress()),
                new Location(entity.getLocation()),
                entity.getContactDetails(),
                new WorkHours(entity.getOpenTime(), entity.getCloseTime()),
                entity.getAmenities().stream().map(AggregateId::from).collect(Collectors.toUnmodifiableList()),
                entity.isActive()
                );
    }

    @Override
    public Facility save(Facility facility) {
        FacilityEntity facilityEntity = jpaFacilityRepository.save(FacilityEntity.from(facility));
        return build(facilityEntity);
    }

    @Override
    public Optional<Facility> findById(AggregateId id) {
        FacilityEntity facilityEntity = jpaFacilityRepository.findById(id.value()).get();
        return Optional.of(build(facilityEntity));
    }

    @Override
    public List<Facility> findAll() {
        return jpaFacilityRepository.findAll().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }
}
