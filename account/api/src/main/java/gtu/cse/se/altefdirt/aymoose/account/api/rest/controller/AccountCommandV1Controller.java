package gtu.cse.se.altefdirt.aymoose.account.api.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.account.api.rest.dto.CreateAccountRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class AccountCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/accounts")
    public Response<AccountView> create(@RequestBody CreateAccountRequestDTO request) {
        AccountView view = runner.run(new CreateAccount(
                request.mailAddress(),
                request.password(),
                request.mailAddress(),
                request.firstName(),
                request.lastName()));

        return Response.success(view, "Account created successfully");
    }
}
