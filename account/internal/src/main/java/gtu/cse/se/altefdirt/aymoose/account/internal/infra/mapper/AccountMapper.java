package gtu.cse.se.altefdirt.aymoose.account.internal.infra.mapper;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa.AccountEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final AccountFactory factory;

    public AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.id().value())
                .firstName(account.fullName().firstName())
                .lastName(account.fullName().lastName())
                .build();
    }

    public Account toDomain(AccountEntity entity) {
        return factory.load(AggregateId.fromUUID(entity.getId()),
                new FullName(
                        entity.getFirstName(),
                        entity.getLastName()),
                new CreatedAt(entity.getCreatedAt()),
                entity.isActive());
    }
}
