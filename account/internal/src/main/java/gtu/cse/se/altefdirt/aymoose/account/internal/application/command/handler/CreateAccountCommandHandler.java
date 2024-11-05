package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.CreateAccount;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.AuthServerOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;

import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import lombok.RequiredArgsConstructor;


@RegisterHandler
@RequiredArgsConstructor
public class CreateAccountCommandHandler implements CommandHandler<CreateAccount, AccountView> {

    private final AccountFactory factory;
    private final AccountService service;
    private final AccountRepository accountRepository;
    private final AuthServerOperationsPort authServerOperationsPort;

    @Override
    public AccountView handle(CreateAccount command) {

        Account account = factory.create(command.username(),  new FullName( command.fullName()), command.profilePicture(), Instant.now());
        
        System.out.println("Account: " + account +  " command: " + command);
        Optional<Boolean> register = authServerOperationsPort.register(account.id().value(), command.username(), command.email(), command.password());

        if (register.isEmpty()) {
            throw new RuntimeException("User registration failed");
        }

        Account savedAccount = accountRepository.save(account);
        return service.denormalize(savedAccount);
    }
}