package gtu.cse.se.altefdirt.aymoose.court.api.rest.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.api.rest.dto.CourtResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ReviewQueryV1Controller {

    private final CourtRepository courtRepository;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String USER = "user";
        private static final String FACILITY = "facility";
        private static final String RATING_EQ = "ratingEq";
        private static final String RATING_GT = "ratingGt";
        private static final String RATING_LT = "ratingLt";
    }
    
    @GetMapping(value = "/courts")
    List<CourtResponseDTO> getAllcourts() {
        return courtRepository.findAll().stream().map(court -> CourtResponseDTO.from(CourtView.fromDomain(court))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/courts/{id}")
    CourtResponseDTO getcourtById(@PathVariable(Parameter.ID) String id) {
        return CourtResponseDTO.from(CourtView.fromDomain(courtRepository.findById(AggregateId.from(id))));
    }

}
