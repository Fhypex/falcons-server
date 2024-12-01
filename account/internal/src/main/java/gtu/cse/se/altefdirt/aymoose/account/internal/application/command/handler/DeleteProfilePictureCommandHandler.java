package gtu.cse.se.altefdirt.aymoose.account.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.command.UpdateProfilePicture;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteProfilePictureCommandHandler implements CommandHandler<UpdateProfilePicture, AggregateId> {

    private final AccountRepository accountRepository;
    private final ImageOperationPort imageOperationPort;

    @Override
    public AggregateId handle(UpdateProfilePicture command) {

        Account account = accountRepository.findById(command.userId())
                .orElseThrow(() -> new RuntimeException("Account not found"));


        imageOperationPort.deleteByRelationId(account.id());

        accountRepository.save(account);

        return account.id();
    }
}