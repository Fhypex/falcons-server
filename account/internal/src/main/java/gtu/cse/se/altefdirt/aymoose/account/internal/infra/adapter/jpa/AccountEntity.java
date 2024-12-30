package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.jpa;

import java.time.Instant;
import java.util.UUID;
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
    private UUID id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Instant createdAt;
    private boolean isActive;
}
