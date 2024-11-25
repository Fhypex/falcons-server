package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.AmenityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.FacilityCompressedResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.FacilityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.CityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
public class FacilityQueryController {

    private final FacilityRepository facilityRepository;
    private final FacilityService facilityService;
    private final AmenityRepository amenityRepository;
    private final DistrictRepository districtRepository;
    private final AmenityService amenityService;
    private final CityRepository cityRepository;
    private final CityService cityService;

    private static final class Parameter {
        private static final String COMPRESSED = "compressed";
        private static final String ID = "id";
        private static final String IN_USE = "inUse";
    }

    @GetMapping(value = "/facilities")
    public List<FacilityResponseDTO> getFacilities() {

        List<FacilityView> facilityViews = facilityRepository.findAll().stream().map(facilityService::denormalize).collect(Collectors.toList());

        return facilityViews.stream().map(FacilityResponseDTO::fromView)
                .collect(Collectors.toList());
    }


    @GetMapping(value = "/facilities", params = Parameter.COMPRESSED)
    public List<FacilityCompressedResponseDTO> getFacilitiesCompressed(
        @RequestParam(Parameter.COMPRESSED) boolean compressed) {

        if(compressed) {
            List<FacilityView> facilityViews = facilityRepository.findAll().stream().map(facilityService::denormalize).collect(Collectors.toList());
            return facilityViews.stream().map(FacilityCompressedResponseDTO::from)
                .collect(Collectors.toList());
        }
        else 
            throw new IllegalArgumentException("Invalid parameter");
    }

     @GetMapping(value = "/facilities/{id}")
    public FacilityResponseDTO getFacility(@PathVariable(Parameter.ID) String id) {
        Facility facility = facilityRepository.findById(new AggregateId(id)).get();
        FacilityView facilityView = facilityService.denormalize(facility);
        return FacilityResponseDTO.fromView(facilityView);
    }

    @GetMapping(value = "/amenities")
    public List<AmenityResponseDTO> getAmenities() {
        
        List<AmenityView> amenityViews = amenityRepository.findAll().stream().map(amenityService::denormalize).collect(Collectors.toList());
            return amenityViews.stream().map(AmenityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());

    }

    @GetMapping(value = "/amenities/{id}")
    public AmenityResponseDTO getAmenityById(@PathVariable(Parameter.ID) String id) {
        AmenityView amenityView = amenityRepository.findById(AggregateId.from(id)).map(amenityService::denormalize).get();
        return AmenityResponseDTO.fromView(amenityView);
    }


    @GetMapping(value = "/cities")
    public List<CityResponseDTO> getCities() {
        List<CityView> cityViews = cityRepository.findAll().stream().map(cityService::denormalize).collect(Collectors.toList());
        return cityViews.stream().map(CityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());
    }  

    @GetMapping(value = "/cities", params = Parameter.IN_USE)
    public List<CityResponseDTO> getCities(@RequestParam(Parameter.IN_USE) boolean inUse) {
        List<District> districts = districtRepository.findAllByInUseTrue();
        
        return cityService.denormalizeInUseCitiesByDistricts(districts).stream().map(CityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());
    }  

    @GetMapping(value = "/cities/{id}")
    public CityResponseDTO getCityById(@PathVariable(Parameter.ID) Long id) {
        CityView cityView = cityRepository.findById(id).map(cityService::denormalize).get();
        return CityResponseDTO.fromView(cityView);
    }
}
