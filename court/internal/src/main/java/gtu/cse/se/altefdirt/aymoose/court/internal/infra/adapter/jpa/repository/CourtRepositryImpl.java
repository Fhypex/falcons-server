package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.WorkHours;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.CourtEntity;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.JpaCourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.JpaAmenityRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa.AmenityEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class CourtRepositryImpl implements CourtRepository {

    private final JpaCourtRepository jpaCourtRepository;
    private final JpaAmenityRepository jpaAmenityRepository;
    private final CourtFactory courtFactory;

    private Court build(CourtEntity courtEntity, List<AmenityEntity> amenityEntity) {
        return courtFactory.load(AggregateId.from(courtEntity.getId()), 
                                 AggregateId.from(courtEntity.getFacilityId()), 
                                 new CourtDetails(courtEntity.getName(), courtEntity.getDescription()),
                                 new Measurements(courtEntity.getHeight(), courtEntity.getWidth()),
                                 new Capacity(courtEntity.getCapacity()),
                                 new WorkHours(courtEntity.getOpenTime(), courtEntity.getCloseTime()),
                                 new Location(courtEntity.getLongitude(), courtEntity.getLatitude()), 
                                 amenityEntity.stream().map(entity -> new Amenity(AggregateId.from(entity.getAmenityId()))).collect(Collectors.toList()),
                                 courtEntity.isActive());
    }

    @Override
    public Court save(Court court) {
        CourtEntity courtEntity = jpaCourtRepository.save(CourtEntity.from(court));
        List<AmenityEntity> amenityEntities = court.getAmenities().stream().map(amenity -> jpaAmenityRepository.save(AmenityEntity.from(amenity))).collect(Collectors.toUnmodifiableList());
        return build(courtEntity, amenityEntities);
    }


    @Override
    public Optional<Court> findById(AggregateId id) {
        CourtEntity courtEntity = jpaCourtRepository.findById(id.value()).get();
        List<AmenityEntity> amenityEntities = jpaAmenityRepository.findByAmenityId(id.value());
        return Optional.of(build(courtEntity, amenityEntities));
    }

    @Override
    public List<Court> findAll() {
        return jpaCourtRepository.findAll().stream().map(entity -> {
            List<AmenityEntity> amenityEntities = jpaAmenityRepository.findByAmenityId(entity.getId());
            return build(entity, amenityEntities);
        }).collect(Collectors.toList());
    }
}