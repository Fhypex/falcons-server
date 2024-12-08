package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto;

public record CreateAccountRequestDTO(
        String firstName,
        String lastName) {

    public CreateAccountRequestDTO(
            String firstName,
            String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}