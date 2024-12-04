package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

@Component
class FacilityOperationAdapter implements FacilityOperationPort {

    public boolean canReserve(AggregateId userId, AggregateId courtId, LocalDate date, int timeSlot) {
        return true;
    }

    public WorkHours getWorkHours(AggregateId courtId) {
        return null;
    }

    public List<CourtData> findByFacilityId(AggregateId facilityId) {
        return null;
    }

    public List<CourtRichData> findByFacilityIdRich(AggregateId facilityId) {
        return null;
    }

}
