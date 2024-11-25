package gtu.cse.se.altefdirt.aymoose.court.api.rest.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.api.rest.dto.CourtResponseDTO;
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
        private static final String USER = "user";
        private static final String FACILITY = "facility";
        private static final String RATING_EQ = "ratingEq";
        private static final String RATING_GT = "ratingGt";
        private static final String RATING_LT = "ratingLt";
    }
    
    @GetMapping(value = "/courts")
    List<CourtResponseDTO> getAllCourts() {
        return courtRepository.findAll().stream().map(court -> CourtResponseDTO.fromView(courtService.denormalize(court))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/courts/{id}")
    CourtResponseDTO getCourtById(@PathVariable(Parameter.ID) String id) {
        Optional<Court> fetch = courtRepository.findById(AggregateId.from(id));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Court does not exist");
        }
        return CourtResponseDTO.fromView(courtService.denormalize(fetch.get()));
    }

    @GetMapping(value = "/courts", params = Parameter.FACILITY)
    List<CourtResponseDTO> getCourtsByFacilityId(@RequestParam(value = Parameter.FACILITY) String facilityId) {
        List<Court> courts = courtRepository.findAllByFacilityId(AggregateId.from(facilityId));
        return courts.stream().map(court -> CourtResponseDTO.fromView(courtService.denormalize(court))).collect(Collectors.toUnmodifiableList());
    }

}
