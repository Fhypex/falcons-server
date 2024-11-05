package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.AccountFactory;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.UpdatedAt;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class AccountEntity {
    
    @Id
    private String id;
    private String username;
    private String fullName;
    private String profilePicture;
    private Instant createdAt;
    private boolean isActive;



    public static AccountEntity fromDomain(Account account) {
        return AccountEntity.builder()
                .id(account.id().value())
                .username(account.username())
                .fullName(account.fullName().value())
                .profilePicture(account.profilePicture())
                .createdAt(account.createdAt())
                .isActive(account.isActive())
                .build();
    }

    public Account toDomain(AccountFactory factory) {
        return factory.load(
                AggregateId.from(id),
                username,
                FullName.of(fullName),
                profilePicture,
                createdAt,
                isActive
        );
    }

}
