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
    public AccountView (Account account, AuthDetails authDetails, ImageData imageData) {
        this(account.id().value(), 
            authDetails.username(), 
            authDetails.mailAddress(),
             account.fullName().value(),
             imageData.image(), 
             account.createdAt().value(),
             account.isActive(),
             authDetails.roles());
    }
}
