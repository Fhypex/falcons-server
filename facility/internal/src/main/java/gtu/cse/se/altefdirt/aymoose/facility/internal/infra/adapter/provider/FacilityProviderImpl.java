package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.provider;

import java.util.Optional;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.FacilityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
class FacilityProviderImpl implements FacilityProvider {

    private final FacilityRepository facilityRepository;
    private final CourtRepository courtRepository;

    private FacilityData build(Facility facility) {
        return FacilityData.builder()
                .id(facility.id())
                .ownerId(facility.userId())
                .name(facility.name())
                .description(facility.description())
                .workHours(facility.workHours())
                .isActive(facility.isActive())
                .build();
    }

    @Override
    public boolean isOwner(AggregateId facilityId, AggregateId userId) {
        return facilityRepository.existsByIdAndOwnerId(facilityId, userId);
    }

    public int deleteById(AggregateId id) {
        return facilityRepository.deleteById(id);
    }

    @Override
    public int deleteByOwnerId(AggregateId userId) {
        facilityRepository.deleteByOwnerId(userId);
        return 1;
    }

    @Override
    public WorkHours getWorkHours(AggregateId facilityId) {
        return facilityRepository.getWorkHours(facilityId);
    }

    @Override
    public WorkHours getWorkHoursByCourtId(AggregateId courtId) {
        return facilityRepository.getWorkHoursByCourtId(courtId);
    }

    @Override
    public boolean existsById(AggregateId id) {
        return facilityRepository.existsById(id);
    }

    @Override
    public String getFacilityName(AggregateId facilityId) {
        log.debug("Getting facility name for facilityId: {}", facilityId);
        Optional<Facility> opt = facilityRepository.findById(facilityId);
        if (opt.isPresent()) {
            log.debug("Facility found: {}", opt.get());
            return opt.get().name();
        }
        return "Sahte Tesis";
    }

    @Override
    public Optional<FacilityData> getFacilityByCourtId(AggregateId courtId) {
        Optional<Court> court = courtRepository.findById(courtId);
        if (court.isEmpty()) {
            return Optional.empty();
        }
        Optional<Facility> facility = facilityRepository.findById(court.get().facilityId());
        return facility.isPresent()
                ? Optional.of(build(facility.get()))
                : Optional.empty();
    }
}
