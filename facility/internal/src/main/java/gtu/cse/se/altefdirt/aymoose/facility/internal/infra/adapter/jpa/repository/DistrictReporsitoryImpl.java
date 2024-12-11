package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaDistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper.DistrictMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class DistrictRepositoryImpl implements DistrictRepository {

    private final JpaDistrictRepository jpaRepository;
    private final DistrictMapper mapper;

    @Override
    public District save(District district) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(district)));
    }

    @Override
    public Optional<District> findById(Long id) {
        return Optional.of(mapper.toDomain(jpaRepository.findById(id).get()));
    }

    @Override
    public List<District> findAll() {
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
    public List<District> findByIds(List<Long> ids) {
        return jpaRepository.findAllById(ids).stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByCityIdAndName(Long cityId, String name) {
        return jpaRepository.existsByCityIdAndName(cityId, name);
    }

    @Override
    public List<District> findByCityId(Long cityId) {
        return jpaRepository.findAllByCityId(cityId).stream().map(mapper::toDomain).toList();
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
