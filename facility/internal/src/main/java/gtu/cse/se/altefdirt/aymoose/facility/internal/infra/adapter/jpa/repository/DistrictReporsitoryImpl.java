package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.DistrictEntity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaDistrictRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class DistrictRepositoryImpl implements DistrictRepository {

    private final JpaDistrictRepository jpaDistrictRepository;
    private final DistrictFactory factory;

    private District build(DistrictEntity entity) {
        return factory.load(
                entity.getId(),
                entity.getCityId(),
                entity.getName(),
                entity.isInUse()
                );
    }

    @Override
    public District save(District district) {
        DistrictEntity facilityEntity = jpaDistrictRepository.save(DistrictEntity.from(district));
        return build(facilityEntity);
    }

    @Override
    public Optional<District> findById(Long id) {
        DistrictEntity facilityEntity = jpaDistrictRepository.findById(id).get();
        return Optional.of(build(facilityEntity));
    }

    @Override
    public List<District> findAll() {
        return jpaDistrictRepository.findAll().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existsByIdIn(List<Long> ids) {
        return jpaDistrictRepository.existsByIdIn(ids);
    }

    @Override
    public boolean exists(Long id) {
        return jpaDistrictRepository.existsById(id);
    }

    @Override
    public List<District> findAll(List<Long> ids) {
        return jpaDistrictRepository.findAllById(ids).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existsByCityIdAndName(Long cityId, String name) {
        return jpaDistrictRepository.existsByCityIdAndName(cityId, name);
    }

    @Override
    public List<District> findAllByCityId(Long cityId) {
        return jpaDistrictRepository.findAllByCityId(cityId).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<District> findAllByInUseTrue() {
        return jpaDistrictRepository.findAllByInUseTrue().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }
}
