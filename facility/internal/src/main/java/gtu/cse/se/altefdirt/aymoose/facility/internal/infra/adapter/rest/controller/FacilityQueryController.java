package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.CityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.AmenityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.FacilityCompressedResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.FacilityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
@Slf4j
public class FacilityQueryController {

    private final FacilityRepository facilityRepository;
    private final FacilityService facilityService;
    private final AmenityRepository amenityRepository;
    private final CourtOperationPort courtOperationPort;
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

        List<FacilityView> facilityViews = facilityRepository.findAll().stream().map(facilityService::denormalize)
                .collect(Collectors.toList());
        List<List<CourtData>> courtData = facilityViews.stream()
                .map(facilityView -> courtOperationPort.findByFacilityId(AggregateId.from(facilityView.id())))
                .collect(Collectors.toList());

        return facilityViews.stream()
                .map(view -> FacilityResponseDTO.fromView(view, courtData.get(facilityViews.indexOf(view))))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/facilities", params = Parameter.COMPRESSED)
    public List<FacilityCompressedResponseDTO> getFacilitiesCompressed(
            @RequestParam(Parameter.COMPRESSED) boolean compressed) {

        if (compressed) {
            List<FacilityView> facilityViews = facilityRepository.findAll().stream().map(facilityService::denormalize)
                    .collect(Collectors.toList());
            return facilityViews.stream().map(FacilityCompressedResponseDTO::from)
                    .collect(Collectors.toList());
        } else
            throw new IllegalArgumentException("Invalid parameter");
    }

    @GetMapping(value = "/facilities/{id}")
    public FacilityResponseDTO getFacility(@PathVariable(Parameter.ID) String id) {
        Facility facility = facilityRepository.findById(new AggregateId(id)).get();
        FacilityView facilityView = facilityService.denormalize(facility);
        List<CourtData> courtData = courtOperationPort.findByFacilityId(AggregateId.from(id));
        return FacilityResponseDTO.fromView(facilityView, courtData);
    }

    @GetMapping(value = "/amenities")
    public List<AmenityResponseDTO> getAmenities() {
        log.debug("Amenity list requested");
        List<AmenityView> amenityViews = amenityRepository.findAll().stream().map(amenityService::denormalize)
                .collect(Collectors.toList());
        return amenityViews.stream().map(AmenityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());

    }

    @GetMapping(value = "/amenities/{id}")
    public AmenityResponseDTO getAmenityById(@PathVariable(Parameter.ID) String id) {
        log.debug("Request to get amenity by id: {}", id);
        AmenityView amenityView = amenityService.denormalize(amenityRepository.findById(AggregateId.from(id)).get());
        log.debug("Amenity found: {}", amenityView);
        return AmenityResponseDTO.fromView(amenityView);
    }

    @GetMapping(value = "/cities")
    public List<CityResponseDTO> getCities() {
        List<CityView> cityViews = cityRepository.findAll().stream().map(cityService::denormalize)
                .collect(Collectors.toList());
        return cityViews.stream().map(CityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/cities", params = Parameter.IN_USE)
    public List<CityResponseDTO> getCities(@RequestParam(Parameter.IN_USE) boolean inUse) {
        Set<Long> districts = facilityRepository.findUniqueDistricts();

        return cityService.denormalizeInUseCitiesByDistricts(districts).stream().map(CityResponseDTO::fromView)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/cities/{id}")
    public CityResponseDTO getCityById(@PathVariable(Parameter.ID) Long id) {
        CityView cityView = cityService.denormalize(cityRepository.findById(id).get());
        return CityResponseDTO.fromView(cityView);
    }
}
