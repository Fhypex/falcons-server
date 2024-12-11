package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.api.provider.FacilityProvider;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class FacilityOperationAdapter implements FacilityOperationPort {

    private final FacilityProvider facilityProvider;

    public boolean canReserve(AggregateId userId, AggregateId courtId, LocalDate date, int timeSlot) {
        return true;
    }

    public WorkHours getWorkHours(AggregateId courtId) {
        return facilityProvider.getWorkHours(courtId);
    }
}
