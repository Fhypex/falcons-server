package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.provider;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class FacilityProviderImpl implements FacilityProvider {

    private final FacilityRepository facilityRepository;

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
}
