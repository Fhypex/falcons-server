package gtu.cse.se.altefdirt.aymoose.account.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.AuthServerOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AuthServerOperationsPort authServiceOperationsPort;
    
    @Override
    public AccountView denormalize(Account account) 
    {
        List<String> roles = authServiceOperationsPort.getRoles(account.id().value());

        return new AccountView(account, roles);
    }
}
