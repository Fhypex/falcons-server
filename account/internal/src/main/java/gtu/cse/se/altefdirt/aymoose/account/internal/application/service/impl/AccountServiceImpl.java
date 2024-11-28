package gtu.cse.se.altefdirt.aymoose.account.internal.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.AuthServerOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountServiceImpl implements AccountService {

    private final AuthServerOperationsPort authServiceOperationsPort;
    private final ImageOperationPort imageOperationPort;

    @Override
    public AccountView denormalize(Account account) {
        log.info("Denormalizing account: {}", account);
        AuthDetails authDetails = authServiceOperationsPort.getDetails(account.id()).get();

        ImageData imageData = imageOperationPort.getImage(account.imageId());

        return new AccountView(account, authDetails, imageData);
    }
}
