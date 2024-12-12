package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FacilityOperationAdapter implements FacilityOperationPort {

    private final FacilityProvider facilityProvider;

    @Override
    public boolean isFacilityExist(AggregateId facilityId) {
        return facilityProvider.existsById(facilityId);
    }
}