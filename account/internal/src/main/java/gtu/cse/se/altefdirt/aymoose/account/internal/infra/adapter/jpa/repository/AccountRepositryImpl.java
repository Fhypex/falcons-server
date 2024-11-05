package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.AccountEntity;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.JpaAccountRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class AccountRepositryImpl implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final AccountFactory accountFactory;

    private Account build(AccountEntity accountEntity) {
        return accountFactory.load(AggregateId.from(accountEntity.getId()), 
                                    accountEntity.getUsername(),
                                    new FullName(accountEntity.getFullName()),
                                    accountEntity.getProfilePicture(),
                                    accountEntity.getCreatedAt(),
                                    accountEntity.isActive());
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = jpaAccountRepository.save(AccountEntity.fromDomain(account));
        return build(accountEntity);
    }


    @Override
    public Optional<Account> findById(AggregateId id) {
        AccountEntity accountEntity = jpaAccountRepository.findById(id.value()).get();
        return Optional.of(build(accountEntity));
    }

    @Override
    public List<Account> findAll() {
        return jpaAccountRepository.findAll().stream().map(entity -> {
            return build(entity);
        }).collect(Collectors.toList());
    }
}