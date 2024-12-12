package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CreateCourtRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.UpdateCourtRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCourt;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.UpdateCourt;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class CourtCommandV1Controller {

    private final CommandRunner runner;

    private static final class Parameter {
        private static final String ID = "id";
    }

    @PostMapping("/courts")
    public Response<UUID> createCourt(@RequestPart(value = "files", required = false) List<MultipartFile> images,
            @RequestPart("data") CreateCourtRequestDTO request) {
        Court court = runner.run(new CreateCourt(
                request.userId(),
                request.facilityId(),
                request.name(),
                request.description(),
                request.height(),
                request.width(),
                request.capacity(),
                request.price(),
                images));
        return Response.success(court.id().value(), "Court created successfully");
    }

    @PatchMapping("/courts/{id}")
    public Response<UUID> updateCourt(@PathVariable(Parameter.ID) UUID id,
            @RequestPart(value = "files", required = false) List<MultipartFile> images,
            @RequestPart("data") UpdateCourtRequestDTO request) {
        Court court = runner.run(new UpdateCourt(
                request.id(),
                request.name(),
                request.description(),
                request.height(),
                request.width(),
                request.capacity(),
                request.price(),
                request.deletedImages(),
                images));
        return Response.success(court.id().value(), "Court updated successfully");
    }
}
