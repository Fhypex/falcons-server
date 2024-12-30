package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import java.util.Optional;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.UpdateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.KeycloakOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class UpdateAccountCommandHandler implements CommandHandler<UpdateAccount, AggregateId> {

    private final AccountRepository accountRepository;
    private final KeycloakOperationPort keycloakOperationPort;

    @Override
    public AggregateId handle(UpdateAccount command) {
        Optional<Account> fetch = accountRepository.findById(command.id());
        if (fetch.isEmpty()) {
            throw new RuntimeException("Could not find account.");
        }
        Account account = fetch.get();

        if (command.phoneNumber() != null && !command.phoneNumber().isBlank()) {
            account.updatePhoneNumber(command.phoneNumber());
        }
        if (command.firstName() != null && !command.firstName().isBlank()) {
            account.updateFirstName(command.firstName());
        }
        if (command.lastName() != null && !command.lastName().isBlank()) {
            account.updateLastName(command.lastName());
        }
        accountRepository.save(account);
        log.debug("Account saved: {}", account);
        return account.id();
    }
}