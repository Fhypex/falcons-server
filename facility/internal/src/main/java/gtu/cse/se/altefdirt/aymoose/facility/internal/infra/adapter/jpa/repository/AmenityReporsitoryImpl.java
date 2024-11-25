package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.AmenityEntity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaAmenityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class AmenityRepositoryImpl implements AmenityRepository {

    private final JpaAmenityRepository jpaAmenityRepository;
    private final AmenityFactory factory;

    private Amenity build(AmenityEntity entity) {
        return factory.load(
                AggregateId.from(entity.getId()),
                entity.getName()
                );
    }

    @Override
    public Amenity save(Amenity facility) {
        AmenityEntity facilityEntity = jpaAmenityRepository.save(AmenityEntity.from(facility));
        return build(facilityEntity);
    }

    @Override
    public Optional<Amenity> findById(AggregateId id) {
        AmenityEntity facilityEntity = jpaAmenityRepository.findById(id.value()).get();
        return Optional.of(build(facilityEntity));
    }

    @Override
    public List<Amenity> findAll() {
        return jpaAmenityRepository.findAll().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existsByIdIn(List<AggregateId> ids) {
        return jpaAmenityRepository.existsByIdIn(ids.stream().map(AggregateId::value).collect(Collectors.toUnmodifiableList()), ids.size());
    }

    @Override
    public boolean exists(AggregateId id) {
        return jpaAmenityRepository.existsById(id.value());
    }

    @Override
    public List<Amenity> findAll(List<AggregateId> ids) {
        return jpaAmenityRepository.findAllById(ids.stream().map(AggregateId::value).collect(Collectors.toUnmodifiableList())).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }
}
