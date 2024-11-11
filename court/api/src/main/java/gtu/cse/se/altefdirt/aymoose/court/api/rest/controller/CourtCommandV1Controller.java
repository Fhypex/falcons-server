package gtu.cse.se.altefdirt.aymoose.court.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.CreateCourt;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.court.api.rest.dto.CreateCourtRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class CourtCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/courts")
    public Response<CourtView> createCourt(@RequestBody CreateCourtRequestDTO request) {
        CourtView view = runner.run(new CreateCourt(request.facilityId(),
                                                    request.name(),
                                                    request.description(),
                                                    request.height(),
                                                    request.width(),
                                                    request.capacity(),
                                                    request.openTime(),
                                                    request.closeTime(),
                                                    request.latitude(),
                                                    request.longitude(),
                                                    request.amenityIds(),
                                                    request.images()));
        return Response.success(view, "Court created successfully");
    }
}
