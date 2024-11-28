package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.AuthServerOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
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
public class CreateAccountCommandHandler implements CommandHandler<CreateAccount, AccountView> {

    private final AccountFactory factory;
    private final AccountService service;
    private final AccountRepository accountRepository;
    private final AuthServerOperationsPort authServerOperationsPort;

    @Override
    public AccountView handle(CreateAccount command) {

        Optional<AggregateId> id = authServerOperationsPort.register(command.username(), command.password(),
                command.mailAddress());

        if (id.isEmpty()) {
            throw new RuntimeException("User registration failed");
        }

        // Mocked image id
        AggregateId imageId = AggregateId.generate();
        Account account = factory.load(id.get(), FullName.of(command.firstName(), command.lastName()), imageId,
                CreatedAt.now(), true);

        log.debug("Account created: {}", account);

        Account savedAccount = accountRepository.save(account);

        log.debug("Account saved: {}", savedAccount);
        return service.denormalize(savedAccount);
    }
}