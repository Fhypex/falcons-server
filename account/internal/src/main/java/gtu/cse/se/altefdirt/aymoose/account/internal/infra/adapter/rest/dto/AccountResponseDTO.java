package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AccountView;
import lombok.Builder;

@Builder
public record AccountResponseDTO(
    UUID id,
    String email,
    String firstName,
    String lastName,
    String profilePicture,
    Instant createdAt,
    List<String> roles
)
{
    public static AccountResponseDTO fromView(AccountView accountView) {
        return AccountResponseDTO.builder()
            .id(accountView.id())
            .email(accountView.mail())
            .firstName(accountView.firstName())
            .lastName(accountView.lastName())
            .profilePicture(accountView.profilePicture())
            .createdAt(accountView.createdAt())
            .roles(accountView.roles())
            .build();
    }
}