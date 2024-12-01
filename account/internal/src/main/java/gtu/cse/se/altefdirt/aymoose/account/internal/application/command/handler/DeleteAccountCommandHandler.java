package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.DeleteAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.KeycloakOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteAccountCommandHandler implements CommandHandler<DeleteAccount, AggregateId> {

    private final AccountRepository accountRepository;
    private final KeycloakOperationPort keycloakOperationPort;

    @Override
    public AggregateId handle(DeleteAccount command) {

        AggregateId accountId = AggregateId.from(command.id());

        int delete = keycloakOperationPort.delete(accountId);

        if (delete == 0) {
            throw new RuntimeException("User deletion failed");
        }
        accountRepository.deleteById(accountId);
        log.debug("Account deleted: {}", command.id());

        return accountId;
    }
}