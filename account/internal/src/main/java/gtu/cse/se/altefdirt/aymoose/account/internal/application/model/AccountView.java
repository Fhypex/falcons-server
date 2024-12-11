package gtu.cse.se.altefdirt.aymoose.account.internal.application.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import lombok.Builder;

@Builder
public record AccountView (
    UUID id,
    String mail,
    String firstName,
    String lastName,
    String profilePicture,
    Instant createdAt,
    Boolean isActive,
    List<String> roles
)
{
    public AccountView (Account account, AuthDetails authDetails, String profilePicture) {
        this(account.id().value(),
            authDetails.mailAddress(),
             account.fullName().firstName(),
             account.fullName().lastName(),
             profilePicture, 
             account.createdAt().value(),
             account.isActive(),
             authDetails.roles());
    }
}
