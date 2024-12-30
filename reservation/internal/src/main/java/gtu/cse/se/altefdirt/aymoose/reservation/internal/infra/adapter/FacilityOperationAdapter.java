package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.FacilityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class FacilityOperationAdapter implements FacilityOperationPort {

    private final FacilityProvider facilityProvider;

    public boolean canReserve(AggregateId userId, AggregateId courtId, Date date, int timeSlot) {
        return true;
    }

    public WorkHours getWorkHoursByCourtId(AggregateId courtId) {
        return facilityProvider.getWorkHoursByCourtId(courtId);
    }

    public Optional<FacilityData> getByCourtId(AggregateId courtId) {
        return facilityProvider.getFacilityByCourtId(courtId);
    }
}
