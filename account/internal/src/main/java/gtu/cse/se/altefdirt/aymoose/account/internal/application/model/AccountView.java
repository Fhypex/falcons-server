package gtu.cse.se.altefdirt.aymoose.account.internal.application.model;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import lombok.Builder;

@Builder
public record AccountView (
    String id,
    String mail,
    String fullName,
    String profilePicture,
    Instant createdAt,
    Boolean isActive,
    List<String> roles
)
{
    public AccountView (Account account, AuthDetails authDetails, String profilePicture) {
        this(account.id().value(), 
            authDetails.mailAddress(),
             account.fullName().value(),
             profilePicture, 
             account.createdAt().value(),
             account.isActive(),
             authDetails.roles());
    }
}
