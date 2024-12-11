package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FacilityServiceImpl implements FacilityService {

        private final ImageOperationPort imageOperationPort;
        private final ReviewOperationPort commentOperationPort;
        private final CourtRepository courtRepository;
        private final AmenityRepository amenityRepository;
        private final AmenityService amenityService;
        private final CityRepository cityRepository;
        private final DistrictRepository districtRepository;

        @Override
        public FacilityView denormalize(Facility facility) {

                List<Amenity> amenities = amenityRepository.findAll(facility.amenities());
                List<AmenityView> amenityViews = amenities.stream().map(amenityService::denormalize).toList();
                List<ImageData> image = imageOperationPort.findByRelationId(facility.id());
                List<String> imageUrls = image.stream().map(ImageData::url).toList();
                int commentCount = commentOperationPort.getReviewCountByFacilityId(facility.id());
                String rating = commentOperationPort.getRatingByFacilityId(facility.id());
                String city = cityRepository.findById(facility.address().cityId()).get().name();
                String district = districtRepository.findById(facility.address().districtId()).get().name();

                List<Court> courts = courtRepository.findByFacilityId(facility.id());

                List<CourtRichData> richCourts = courts.stream().map(this::buildRich)
                                .toList();

                int lowerPriceLimit = richCourts.stream().map(CourtRichData::price).min(Integer::compareTo).orElse(0);
                int upperPriceLimit = richCourts.stream().map(CourtRichData::price).max(Integer::compareTo).orElse(0);

                return new FacilityView(facility, imageUrls, commentCount, rating, city, district, amenityViews,
                                richCourts,
                                lowerPriceLimit, upperPriceLimit);
        }

        private CourtRichData buildRich(Court court) {

                List<String> imageUrls = imageOperationPort.findByRelationId(court.id()).stream()
                                .map(ImageData::url).toList();
                return CourtRichData.builder()
                                .id(court.id())
                                .facilityId(court.getFacilityId())
                                .name(court.getDetails().name())
                                .description(court.getDetails().description())
                                .height(court.getMeasurements().height())
                                .width(court.getMeasurements().width())
                                .capacity(court.getCapacity().value())
                                .price(court.getPrice().value())
                                .imageUrls(imageUrls)
                                .build();
        }
}
