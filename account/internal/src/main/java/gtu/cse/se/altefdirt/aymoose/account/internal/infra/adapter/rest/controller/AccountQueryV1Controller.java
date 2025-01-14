package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto.AccountResponseDTO;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.access.AccessAdmin;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUser;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class AccountQueryV1Controller {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    private static final class Parameter {
        private static final String ID = "id";
    }

    @GetMapping(value = "/accounts")
    @AccessAdmin
    List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> AccountResponseDTO.fromView(accountService.denormalize(account)))
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/account/{id}")
    AccountResponseDTO getAccountById(@PathVariable(Parameter.ID) UUID id) {
        Optional<Account> fetch = accountRepository.findById(AggregateId.fromUUID(id));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return AccountResponseDTO.fromView(accountService.denormalize(fetch.get()));
    }

    @GetMapping(value = "/account/my")
    AccountResponseDTO getAccountById(@AuthenticationPrincipal JwtUser user) {
        Optional<Account> fetch = accountRepository.findById(user.id());
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return AccountResponseDTO.fromView(accountService.denormalize(fetch.get()));
    }
}
