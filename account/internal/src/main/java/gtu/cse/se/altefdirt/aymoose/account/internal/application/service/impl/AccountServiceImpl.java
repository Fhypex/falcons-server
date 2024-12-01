package gtu.cse.se.altefdirt.aymoose.account.internal.application.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.KeycloakOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountServiceImpl implements AccountService {

    private final KeycloakOperationPort authServiceOperationsPort;
    private final ImageOperationPort imageOperationPort;

    @Override
    public AccountView denormalize(Account account) {
        log.info("Denormalizing account: {}", account);
        AuthDetails authDetails = authServiceOperationsPort.getDetails(account.id()).get();
        Optional<ImageData> imageData = imageOperationPort.findOneByRelationId(account.id());

        if(imageData.isEmpty()) {
            return new AccountView(account, authDetails, this.randomProfilePicture());
        }
        return new AccountView(account, authDetails, imageData.get().url());
    }

    @Override
    public String randomProfilePicture() {
        return "https://img.icons8.com/keek/100/cat.png";
    }

}