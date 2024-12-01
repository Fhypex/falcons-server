package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.provider;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
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
}
