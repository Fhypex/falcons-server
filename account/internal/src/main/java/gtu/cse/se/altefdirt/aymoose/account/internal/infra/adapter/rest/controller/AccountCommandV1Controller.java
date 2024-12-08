package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.UpdateProfilePicture;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto.CreateAccountRequestDTO;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUtils;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.SecuredUser;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class AccountCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/accounts")
    public Response<AggregateId> create(@AuthenticationPrincipal SecuredUser user, @RequestBody CreateAccountRequestDTO request) {
        AggregateId id = runner.run(new CreateAccount(
                user.id(),
                request.firstName(),
                request.lastName()));

        return Response.success(id, "Account created successfully");
    }


    @PostMapping("/account/my/profile-picture")
    public Response<AggregateId> updateProfilePicture(@AuthenticationPrincipal SecuredUser user, @RequestPart("image") MultipartFile request) {
        AggregateId id = runner.run(new UpdateProfilePicture(user.id(), request));
        return Response.success(id, "Account created successfully");
    }
}
