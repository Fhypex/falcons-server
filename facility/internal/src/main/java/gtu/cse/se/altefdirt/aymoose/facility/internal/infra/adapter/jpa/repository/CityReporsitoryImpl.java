package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.CityEntity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaCityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class CityRepositoryImpl implements CityRepository {

    private final JpaCityRepository jpaCityRepository;
    private final CityFactory factory;

    private City build(CityEntity entity) {
        return factory.load(
                entity.getId(),
                entity.getName()
                );
    }

    @Override
    public City save(City facility) {
        CityEntity facilityEntity = jpaCityRepository.save(CityEntity.from(facility));
        return build(facilityEntity);
    }

    @Override
    public Optional<City> findById(Long id) {
        CityEntity facilityEntity = jpaCityRepository.findById(id).get();
        return Optional.of(build(facilityEntity));
    }

    @Override
    public List<City> findAll() {
        return jpaCityRepository.findAll().stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existsByIdIn(List<Long> ids) {
        return jpaCityRepository.existsByIdIn(ids);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaCityRepository.existsById(id);
    }

    @Override
    public List<City> findAll(List<Long> ids) {
        return jpaCityRepository.findAllById(ids).stream().map(this::build).collect(Collectors.toUnmodifiableList());
    }
}
