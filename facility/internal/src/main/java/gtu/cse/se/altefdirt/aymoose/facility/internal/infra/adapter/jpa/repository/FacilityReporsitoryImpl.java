package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.FacilityEntity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaCourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.JpaFacilityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper.FacilityMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
class FacilityRepositoryImpl implements FacilityRepository {

    private final JpaFacilityRepository jpaRepository;
    private final JpaCourtRepository jpaCourtRepository;
    private final FacilityMapper mapper;

    @Override
    public Facility save(Facility facility) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(facility)));
    }

    @Override
    public Optional<Facility> findById(AggregateId id) {
        return jpaRepository.findById(id.value()).isPresent()
                ? Optional.of(mapper.toDomain(jpaRepository.findById(id.value()).get()))
                : Optional.empty();
    }

    @Override
    public List<Facility> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Set<Long> findUniqueDistricts() {
        return jpaRepository.findAll().stream().map(FacilityEntity::getDistrictId)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean existsById(AggregateId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public List<Facility> findByIds(List<AggregateId> ids) {
        return jpaRepository.findAllById(ids.stream().map(AggregateId::value).toList()).stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public int deleteById(AggregateId id) {
        jpaRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return jpaRepository.existsByIds(ids.stream().map(AggregateId::value).toList(), ids.size());
    }

    @Override
    public boolean hasFacilityByDistrictId(Long districtId) {
        return jpaRepository.existsByDistrictId(districtId);
    }

    @Override
    public boolean hasFacilityByDistrictIds(List<Long> districtIds) {
        return jpaRepository.existsByDistrictIds(districtIds);
    }

    @Override
    public boolean existsByIdAndOwnerId(AggregateId id, AggregateId userId) {
        return jpaRepository.existsByIdAndOwnerId(id.value(), userId.value());
    }

    @Override
    public int deleteByOwnerId(AggregateId userId) {
        jpaRepository.deleteByOwnerId(userId.value());
        return 1;
    }

    @Override
    public WorkHours getWorkHours(AggregateId facilityId) {
        FacilityEntity facilityEntity = jpaRepository.findById(facilityId.value()).orElseThrow(
                () -> new RuntimeException("Facility not found"));
        return new WorkHours(facilityEntity.getOpenTime(), facilityEntity.getCloseTime());
    }

    @Override
    public WorkHours getWorkHoursByCourtId(AggregateId courtId) {
        log.debug("Getting work hours by court id {}", courtId);
        UUID facilityId = jpaCourtRepository.findById(courtId.value()).orElseThrow(
                () -> new RuntimeException("Court not found")).getFacilityId();
        log.debug("Facility id found {}", facilityId);
        FacilityEntity facilityEntity = jpaRepository.findById(facilityId).orElseThrow(
                () -> new RuntimeException("Facility not found"));
        return new WorkHours(facilityEntity.getOpenTime(), facilityEntity.getCloseTime());
    }

    @Override
    @Transactional
    public int deleteAmenityFromAllFacilities(AggregateId amenityId) {
        // Find all facilities that have the given amenity ID
        List<FacilityEntity> facilities = jpaRepository.findFacilitiesByAmenityId(amenityId.value());
        // Loop through each facility and remove the amenity from the set
        for (FacilityEntity facility : facilities) {
            facility.getAmenities().remove(amenityId.value());
        }
        return facilities.size();
    }
}
