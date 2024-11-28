package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.api.provider.CourtProvider;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class CourtOperationAdapter implements CourtOperationPort {

    private final CourtProvider courtProvider;

    @Override
    public int deleteByFacilityId(AggregateId facilityId) {
        return courtProvider.deleteByFacilityId(facilityId);
    }

    @Override
    public List<CourtData> findByFacilityId(AggregateId facilityId) {
        return courtProvider.getCourtsByFacilityId(facilityId);
    }

    @Override
    public List<CourtRichData> findByFacilityIdRich(AggregateId facilityId) {
        return courtProvider.getCourtsByFacilityIdRich(facilityId);
    }
}
