package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.AmenityOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class AmenityOperationsAdapter implements AmenityOperationsPort {

    @Override
    public AmenityData get(AggregateId id) {
        return new AmenityData("somem-mocked-amenity-image", "some-mocked-amenity-title");
    }

    @Override
    public boolean exists(List<String> ids) {
        return true;
    }
    
    @Override
    public List<AmenityData> findAll(List<AggregateId> ids) {
        return List.of(new AmenityData("somem-mocked-amenity-image", "some-mocked-amenity-title"));
    }
}

