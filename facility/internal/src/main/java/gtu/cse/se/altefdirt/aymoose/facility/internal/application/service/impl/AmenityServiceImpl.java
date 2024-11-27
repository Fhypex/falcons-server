package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AmenityServiceImpl implements AmenityService {

    private final ImageOperationPort imageOperationPort;
    private final AmenityRepository amenityRepository;

    @Override
    public AmenityView denormalize(Amenity amenity) {

        ImageData image = imageOperationPort.findOneByRelationId(amenity.id());

        return new AmenityView(amenity, image.url());
    }

    @Override
    public boolean validateAmenities(List<AggregateId> amenities) {
        return amenityRepository.existsByIdIn(amenities);
    }
}
