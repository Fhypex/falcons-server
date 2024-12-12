package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountRepository;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.AccountEntity;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.JpaAccountRepository;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.mapper.AccountMapper;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class AccountRepositryImpl implements AccountRepository {

    private final JpaAccountRepository accountRepository;
    private final AccountMapper mapper;

    @Override
    public Account save(Account account) {
        return mapper.toDomain(accountRepository.save(mapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findById(AggregateId id) {
        Optional<AccountEntity> opt = accountRepository.findById(id.value());
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDomain(opt.get()));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public int deleteById(AggregateId id) {
        accountRepository.deleteById(id.value());
        return 1;
    }

    @Override
    public List<Account> findByIds(List<AggregateId> ids) {
        return accountRepository.findAllById(ids.stream().map(AggregateId::value).toList()).stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(AggregateId id) {
        return accountRepository.existsById(id.value());
    }

    @Override
    public boolean existsByIds(List<AggregateId> ids) {
        return accountRepository.existsByIds(ids.stream().map(AggregateId::value).toList(), ids.size());
    }
}