package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FacilityServiceImpl implements FacilityService {

    private final ImageOperationPort imageOperationsPort;
    private final ReviewOperationPort commentOperationPort;
    private final CourtOperationPort courtOperationPort;


    @Override
    public FacilityView denormalize(Facility facility) {

        ImageData image = imageOperationsPort.find(facility.id());

        int commentCount = commentOperationPort.reviewCount(facility.id());

        String rating = commentOperationPort.rating(facility.id());

        List<AmenityData> amenities = courtOperationPort.findAllAmenities(facility.id());

        return new FacilityView(facility, image.url(), commentCount, rating, amenities);
    }
}
