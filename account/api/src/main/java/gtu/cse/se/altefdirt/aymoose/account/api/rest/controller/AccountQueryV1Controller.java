package gtu.cse.se.altefdirt.aymoose.account.api.rest.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.account.api.rest.dto.AccountResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class AccountQueryV1Controller {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String USER = "account";
        private static final String FACILITY = "facility";
        private static final String RATING_EQ = "ratingEq";
        private static final String RATING_GT = "ratingGt";
        private static final String RATING_LT = "ratingLt";
    }
    
    @GetMapping(value = "/accounts")
    List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(account -> AccountResponseDTO.fromView(accountService.denormalize(account))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/account/{id}")
    AccountResponseDTO getAccountById(@PathVariable(Parameter.ID) String id) {
        Optional<Account> fetch = accountRepository.findById(AggregateId.from(id));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return AccountResponseDTO.fromView(accountService.denormalize(fetch.get()));
    }

}
