package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationsPort1;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FacilityServiceImpl implements FacilityService {

    private final ImageOperationsPort1 imageOperationsPort;
    
    @Override
    public FacilityView denormalize(Facility facility) 
    {
        
        List<ImageData> images = imageOperationsPort.findAll(facility.id());

        List<String> imagePaths = images.stream().map(ImageData::id).collect(Collectors.toList());

        return FacilityView.builder()
            .id(facility.id().value())
            .userId(facility.userId().value())
            .facilityName(facility.facilityName())
            .phoneNumber(facility.phoneNumber())
            .facilityDescription(facility.facilityDescription())
            .location(facility.location())  
            .city(facility.city())          
            .district(facility.district())  
            .contactDetails(facility.contactDetails())
            .courtCount(facility.courtCount().value())
            .isActive(facility.isActive())
            .images(imagePaths)
            .build();
    }
}
