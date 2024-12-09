package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto;

public record UpdateAccountRequestDTO(
        String firstName,
        String lastName) {

    public UpdateAccountRequestDTO(
            String firstName,
            String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}