package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.UpdateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.UpdateProfilePicture;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto.CreateAccountRequestDTO;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUser;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
class AccountCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/accounts")
    public Response<AggregateId> create(@AuthenticationPrincipal JwtUser user,
            @RequestBody CreateAccountRequestDTO request) {
        AggregateId id = runner.run(new CreateAccount(
                user.id(),
                request.firstName(),
                request.lastName()));

        return Response.success(id, "Account created successfully");
    }

    @PatchMapping("/accounts/my")
    public Response<AggregateId> update(@AuthenticationPrincipal JwtUser user,
            @RequestBody CreateAccountRequestDTO request) {
        AggregateId id = runner.run(new UpdateAccount(
                user.id(),
                request.firstName(),
                request.lastName()));

        return Response.success(id, "Account created successfully");
    }

    @PatchMapping("/account/my/profile-picture")
    public Response<AggregateId> updateProfilePicture(@AuthenticationPrincipal JwtUser user,
            @RequestPart("image") MultipartFile image) {
        log.info("Updating profile picture for user {}", user.id());
        AggregateId id = runner.run(new UpdateProfilePicture(user.id(), image));
        return Response.success(id, "Profile picture changed successfully");
    }
}
