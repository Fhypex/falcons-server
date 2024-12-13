package gtu.cse.se.altefdirt.aymoose.account.internal.infra.provider;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.account.api.provider.AccountProvider;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.service.AccountService;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class AccountProviderImpl implements AccountProvider {

    private final AccountRepository accountRepository;
    private final AccountService service;

    @Override
    public UserData getAccountById(AggregateId id) {
        Optional<Account> opt = accountRepository.findById(id);
        if (opt.isPresent()) {
            Account account = opt.get();
            AccountView view = service.denormalize(account);
            return new UserData(account.id(), account.fullName(), view.profilePicture());
        }
        return new UserData(id, FullName.of("Inaho", "Kazuki"),
                "https://avatarfiles.alphacoders.com/766/76686.png");
    }

    @Override
    public List<UserData> getAccountsByIds(List<AggregateId> ids) {
        List<Account> accounts = accountRepository.findByIds(ids);
        return ids.stream().map(id -> {
            Optional<Account> opt = accounts.stream().filter(account -> account.id().equals(id)).findFirst();
            if (opt.isPresent()) {
                Account account = opt.get();
                AccountView view = service.denormalize(account);
                return new UserData(account.id(), account.fullName(), view.profilePicture());
            }
            return new UserData(id, FullName.of("Inaho", "Kazuki"),
                    "https://avatarfiles.alphacoders.com/766/76686.png");
        }).toList();
    }
}
