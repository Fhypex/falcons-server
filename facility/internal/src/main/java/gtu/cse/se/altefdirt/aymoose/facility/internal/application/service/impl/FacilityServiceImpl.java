package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FacilityServiceImpl implements FacilityService {

    private final ImageOperationPort imageOperationPort;
    private final ReviewOperationPort commentOperationPort;
    private final CourtOperationPort courtOperationPort;
    private final AmenityRepository amenityRepository;
    private final AmenityService amenityService;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    @Override
    public FacilityView denormalize(Facility facility) {

        List<Amenity> amenities = amenityRepository.findAll(facility.amenities());
        List<AmenityView> amenityViews = amenities.stream().map(amenityService::denormalize)
                .collect(Collectors.toUnmodifiableList());
        List<ImageData> image = imageOperationPort.findByRelationId(facility.id());
        List<String> imageUrls = image.stream().map(ImageData::url).collect(Collectors.toList());
        int commentCount = commentOperationPort.reviewCount(facility.id());
        String rating = commentOperationPort.rating(facility.id());
        String city = cityRepository.findById(facility.address().cityId()).get().name();
        String district = districtRepository.findById(facility.address().districtId()).get().name();

        List<CourtData> courts = courtOperationPort.findByFacilityId(facility.id());

        return new FacilityView(facility, imageUrls, commentCount, rating, city, district, amenityViews);
    }
}
