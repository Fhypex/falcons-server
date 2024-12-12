package gtu.cse.se.altefdirt.aymoose.account.internal.infra.provider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.api.provider.AccountProvider;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class AccountProviderImpl implements AccountProvider {

    private final AccountRepository accountRepository;

    @Override
    public FullName getFullNameById(AggregateId id) {
        Optional<Account> opt = accountRepository.findById(id);
        if (opt.isPresent()) {
            Account account = opt.get();
            return new FullName(account.fullName().firstName(), account.fullName().lastName());
        }
        return new FullName("Sahte", "Kullanıcı");
    }

    @Override
    public List<FullName> getFullNamesByIds(List<AggregateId> ids) {
        List<Account> accounts = accountRepository.findByIds(ids);
        return ids.stream().map(id -> {
            Optional<Account> opt = accounts.stream().filter(account -> account.id().equals(id)).findFirst();
            if (opt.isPresent()) {
                Account account = opt.get();
                return new FullName(account.fullName().firstName(), account.fullName().lastName());
            }
            return new FullName("Sahte", "Kullanıcı");
        }).toList();
    }
}
