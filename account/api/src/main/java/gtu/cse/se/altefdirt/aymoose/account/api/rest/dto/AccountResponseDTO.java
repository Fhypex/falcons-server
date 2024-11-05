package gtu.cse.se.altefdirt.aymoose.account.api.rest.dto;

import java.time.Instant;
import java.util.List;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import lombok.Builder;

@Builder
public record AccountResponseDTO(
    String id,
    String username,
    String email,
    String fullName,
    String profilePicture,
    Instant createdAt,
    List<String> roles
)
{
    public static AccountResponseDTO fromView(AccountView accountView) {
        return AccountResponseDTO.builder()
            .id(accountView.id())
            .username(accountView.username())
            .email(accountView.mail())
            .fullName(accountView.fullName())
            .profilePicture(accountView.profilePicture())
            .createdAt(accountView.createdAt())
            .roles(accountView.roles())
            .build();
    }
}