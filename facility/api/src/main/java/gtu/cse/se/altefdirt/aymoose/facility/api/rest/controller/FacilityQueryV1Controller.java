
/* 
package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.FacilityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.FacilityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.FacilityResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FacilityQueryV1Controller {

    private final FacilityRepository facilityRepository;
    private final FacilityService facilityService;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String USER = "user";
        private static final String LOCATION = "location";
        private static final String COURT_COUNT_GT = "courtCountGt";
        private static final String COURT_COUNT_LT = "courtCountLt";
    }

    @GetMapping(value = "/facilities")
    List<FacilityResponseDTO> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(facility -> FacilityResponseDTO.fromView(facilityService.denormalize(facility)))
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/facility/{id}")
    FacilityResponseDTO getFacilityById(@PathVariable(Parameter.ID) String id) {
        Optional<Facility> fetch = facilityRepository.findById(AggregateId.from(id));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Facility does not exist");
        }
        return FacilityResponseDTO.fromView(facilityService.denormalize(fetch.get()));
    }
}
*/
