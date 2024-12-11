package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CourtResponseDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class CourtQueryV1Controller {

    private final CourtRepository courtRepository;
    private final CourtService courtService;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String FACILITY = "facility";
    }

    @GetMapping(value = "/courts")
    List<CourtResponseDTO> getAllCourts() {
        return courtRepository.findAll().stream()
                .map(court -> CourtResponseDTO.fromView(courtService.denormalize(court))).toList();
    }

    @GetMapping(value = "/courts/{id}")
    CourtResponseDTO getCourtById(@PathVariable(Parameter.ID) UUID id) {
        Optional<Court> fetch = courtRepository.findById(AggregateId.fromUUID(id));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Court does not exist");
        }
        return CourtResponseDTO.fromView(courtService.denormalize(fetch.get()));
    }

    @GetMapping(value = "/courts", params = Parameter.FACILITY)
    List<CourtResponseDTO> getCourtsByFacilityId(@RequestParam(value = Parameter.FACILITY) UUID facilityId) {
        List<Court> courts = courtRepository.findByFacilityId(AggregateId.fromUUID(facilityId));
        return courts.stream().map(court -> CourtResponseDTO.fromView(courtService.denormalize(court))).toList();
    }

}
