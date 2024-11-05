package gtu.cse.se.altefdirt.aymoose.account.internal.application.model;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.account.internal.domain.Account;
import lombok.Builder;

@Builder
public record AccountView (
    String id,
    String username,
    String mail,
    String fullName,
    String profilePicture,
    Instant createdAt,
    Boolean isActive,
    List<String> roles
)
{
    public AccountView (Account account, List<String> roles) {
        this(account.id().value(), 
             account.username(), 
             "email",
             account.fullName().value(),
             account.profilePicture(), 
             account.createdAt(),
             account.isActive(),
             roles);
    }
}
