package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateAccountCommandHandler implements CommandHandler<CreateAccount, AggregateId> {

    private final AccountFactory factory;
    private final AccountRepository accountRepository;

    @Override
    public AggregateId handle(CreateAccount command) {

        Account account = factory.load(command.id(), FullName.of(command.firstName(), command.lastName()),
                CreatedAt.now(), true);

        log.debug("Account created: {}", account);

        Account savedAccount = accountRepository.save(account);

        log.debug("Account saved: {}", savedAccount);
        return savedAccount.id();
    }
}