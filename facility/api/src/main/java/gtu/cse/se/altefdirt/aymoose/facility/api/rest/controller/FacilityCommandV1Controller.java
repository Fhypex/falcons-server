package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateFacilityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FacilityCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/facilities")
    public Response<FacilityView> createFacility(@RequestBody CreateFacilityRequestDTO request) {
        FacilityView view = runner.run(new CreateFacility(
                request.userId(),
                request.facilityName(),
                request.phoneNumber(),
                request.facilityDescription(),
                request.city(),
                request.district(),
                request.location(),
                request.contactDetails(),
                request.courtCount()));
        return Response.success(view, "Facility created successfully");
    }
}
