package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaCityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper.CityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class CityRepositoryImpl implements CityRepository {

    private final JpaCityRepository jpaRepository;
    private final CityMapper mapper;

    @Override
    public City save(City city) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(city)));
    }

    @Override
    public Optional<City> findById(Long id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id).get()));
    }

    @Override
    public List<City> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByIdIn(List<Long> ids) {
        return jpaRepository.existsByIdIn(ids);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<City> findByIds(List<Long> ids) {
        return jpaRepository.findAllById(ids).stream().map(mapper::toDomain).toList();
    }

    @Override
    public int deleteById(Long id) {
        jpaRepository.deleteById(id);
        return 1;
    }

    @Override
    public boolean existsByIds(List<Long> ids) {
        return jpaRepository.existsByIdIn(ids);
    }
}
