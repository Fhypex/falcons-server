package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUser;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.ApproveReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CancelReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateLocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.RejectReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto.CreateClosedReservationRequestDTO;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto.CreateLocalReservationRequestDTO;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto.CreateReservationRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
class ReservationCommandV1Controller {

    private static final class Parameter {
        private static final String ID = "id";
        private static final String TYPE = "type";
    }

    private final CommandRunner runner;

    @PostMapping(value = "/reservations")
    public Response<UUID> createReservation(@AuthenticationPrincipal JwtUser user,
            @RequestBody @Valid CreateReservationRequestDTO request) {
        log.debug("Creating default reservation for user: {}", user);
        Reservation reservation = runner.run(new CreateReservation(
                user.id(),
                request.courtId(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Reservation created successfully");
    }

    @PostMapping(value = "/reservations", params = Parameter.TYPE + "=local")
    public Response<UUID> createLocalReservation(@AuthenticationPrincipal JwtUser user,
            @RequestBody CreateLocalReservationRequestDTO request) {
        LocalReservation reservation = runner.run(new CreateLocalReservation(
                request.courtId(),
                request.fullName(),
                request.phoneNumber(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Local reservation created successfully");
    }

    @PostMapping(value = "/reservations", params = Parameter.TYPE + "=closed")
    public Response<UUID> createClosedReservation(@RequestBody CreateClosedReservationRequestDTO request) {
        ClosedReservation reservation = runner.run(new CreateClosedReservation(
                request.courtId(),
                request.date(),
                request.hour()));
        return Response.success(reservation.id().value(), "Local reservation created successfully");
    }

    @PostMapping("/reservations/{id}/reject")
    public Response<UUID> rejectReservation(@PathVariable(Parameter.ID) UUID id) {
        Reservation reservation = runner.run(new RejectReservation(id));
        return Response.success(reservation.id().value(), "Reservation rejected successfully");
    }

    @PatchMapping("/reservation/{id}/cancel")
    public Response<UUID> cancelReservation(@PathVariable(Parameter.ID) UUID id) {
        Reservation reservation = runner.run(new CancelReservation(id));
        return Response.success(reservation.id().value(), "Reservation cancelled successfully");
    }

    @PatchMapping("/reservation/{id}/approve")
    public Response<UUID> approveReservation(@PathVariable(Parameter.ID) UUID id) {
        Reservation reservation = runner.run(new ApproveReservation(id));
        return Response.success(reservation.id().value(), "Reservation approved successfully");
    }

    @DeleteMapping("/reservation/{id}/delete-local")
    public Response<Integer> deleteLocalReservation(@PathVariable(Parameter.ID) UUID id) {
        Integer deleted = runner.run(new CancelReservation(id));
        return Response.success(deleted, "Local reservation deleted successfully");
    }

    @DeleteMapping("/reservation/{id}/delete-closed")
    public Response<Integer> deleteClosedReservation(@PathVariable(Parameter.ID) UUID id) {
        Integer deleted = runner.run(new CancelReservation(id));
        return Response.success(deleted, "Closed reservation deleted successfully");
    }
}
