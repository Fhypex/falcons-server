package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.FacilityCompressedResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/facilities")
    public List<FacilityCompressedResponseDTO> getFacilities(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "name", required = false) String name) {

        List<FacilityView> facilityViews = facilityRepository.findAll().stream().map(facility -> facilityService.denormalize(facility)).collect(Collectors.toList());

        return facilityViews.stream().map(facility -> FacilityCompressedResponseDTO.from(facility))
                .collect(Collectors.toList());
    }


    
}
