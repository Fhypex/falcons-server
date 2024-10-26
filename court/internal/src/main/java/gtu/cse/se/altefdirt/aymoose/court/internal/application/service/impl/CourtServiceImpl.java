package gtu.cse.se.altefdirt.aymoose.court.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.AmenityOperationsPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CourtServiceImpl implements CourtService {

    private final AmenityOperationsPort amenityOperationsPort;
    private final ImageOperationsPort imageOperationsPort;
    
    @Override
    public CourtView denormalize(Court court) 
    {
        List<AmenityData> amenities = amenityOperationsPort.findAll(court.amenities().stream().map(amenity -> amenity.amenityId()).collect(Collectors.toList()));
        List<ImageData> images = imageOperationsPort.findAll(court.id());

        List<List<String>> amenitiesList = amenities.stream().map(amenity -> List.of(amenity.image(), amenity.title())).collect(Collectors.toList());
        List<String> imagePaths = images.stream().map(image -> image.id()).collect(Collectors.toList());

        return CourtView.builder()
            .id(court.id().value())
            .name(court.details().name())
            .description(court.details().description())
            .height(court.measurements().height())
            .width(court.measurements().width())
            .capacity(court.capacity().value())
            .openTime(court.workHours().openTime())
            .closeTime(court.workHours().closeTime())
            .amenities(amenitiesList)
            .images(imagePaths)
            .build();
    }
}
