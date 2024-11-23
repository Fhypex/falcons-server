package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class CourtOperationAdapter implements CourtOperationPort {

    @Override
    public List<AmenityData> findAllAmenities(AggregateId id) {
        return List.of(new AmenityData("Amenity 1", "https://www.google.com"),
                       new AmenityData("Amenity 2", "https://www.google.com"));
        
    }
}

