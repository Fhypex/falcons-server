package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.Builder;

import java.util.List;AggregateId.generate(),userId,phoneNumber,facilityName,facilityDescription,location,city,contactDetails,true

@Builder
public record FacilityView(

        String id,
        String ownerId,
        String phoneNumber,
        String facilityName,

        String facilityDescription,
        String location,
        String city,
        String district,
        String contactDetails,
        Integer courtCount,
        boolean isActive,
        List<String> images) {
    public FacilityView(Facility facility, List<String> images) {
        this(facility.id().value(),
                facility.userId().value(),
                facility.facilityName(),
                facility.phoneNumber(),
                facility.facilityDescription(),
                facility.location(),
                facility.city(),
                facility.district(),
                facility.contactDetails(),
                facility.courtCount().value(),
                facility.isActive(),
                images);
    }
}
