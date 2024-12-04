package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.SecuredUser;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.ApproveReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CancelReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateLocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.RejectReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto.CreateReservationRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ReservationCommandV1Controller {

    private static final class Parameter {
        private static final String ID = "id";
        private static final String TYPE = "type";
    }

    private final CommandRunner runner;

    @PostMapping(value = "/reservations", params = Parameter.TYPE + "=default")
    public Response<String> createReservation(@AuthenticationPrincipal SecuredUser user, @RequestBody @Valid CreateReservationRequestDTO request) {
        Reservation reservation = runner.run(new CreateReservation(
                user.id(),
                request.courtId(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Reservation created successfully");
    }

    @PostMapping(value = "/reservations", params = Parameter.TYPE + "=local")
    public Response<String> createLocalReservation(@AuthenticationPrincipal SecuredUser user, @RequestBody CreateReservationRequestDTO request) {
        Reservation reservation = runner.run(new CreateLocalReservation(
                request.courtId(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Local reservation created successfully");
    }

    @PostMapping(value = "/reservations", params = Parameter.TYPE + "=closed")
    public Response<String> createLocalReservation(@RequestBody CreateReservationRequestDTO request) {
        Reservation reservation = runner.run(new CreateClosedReservation(
                request.courtId(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Local reservation created successfully");
    }

    @PostMapping("/reservations")
    public Response<String> rejectReservation(@PathVariable(Parameter.ID) String id) {
        AggregateId facilityId = runner.run(new RejectReservation(id));
        return Response.success(facilityId.value(), "Reservation rejected successfully");
    }

    @PatchMapping("/reservation/{id}/cancel")
    public Response<String> cancelReservation(@PathVariable(Parameter.ID) String id) {
        AggregateId facilityId = runner.run(new CancelReservation(id));
        return Response.success(facilityId.value(), "Reservation cancelled successfully");
    }

    @PatchMapping("/reservation/{id}/approve")
    public Response<String> approveReservation(@PathVariable(Parameter.ID) String id) {
        AggregateId facilityId = runner.run(new ApproveReservation(id));
        return Response.success(facilityId.value(), "Reservation approved successfully");
    }

    @PatchMapping("/reservation/{id}/reject")
    public Response<String> rejectReservation(@PathVariable(Parameter.ID) String id) {
        AggregateId facilityId = runner.run(new RejectReservation(id));
        return Response.success(facilityId.value(), "Reservation rejected successfully");
    }
}
