package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaAmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper.AmenityMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class AmenityRepositoryImpl implements AmenityRepository {

    private final JpaAmenityRepository jpaRepository;
    private final AmenityMapper mapper;

    @Override
    public Amenity save(Amenity facility) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(facility)));
    }

    @Override
    public Optional<Amenity> findById(AggregateId id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()));
    }

    @Override
    public List<Amenity> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByIdIn(List<AggregateId> ids) {
        return jpaRepository.existsByIdIn(
                ids.stream().map(AggregateId::value).toList(), ids.size());
    }

    @Override
    public boolean exists(AggregateId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public List<Amenity> findAll(List<AggregateId> ids) {
        return jpaRepository
                .findAllById(ids.stream().map(AggregateId::value).toList()).stream()
                .map(mapper::toDomain).toList();
    }
}
