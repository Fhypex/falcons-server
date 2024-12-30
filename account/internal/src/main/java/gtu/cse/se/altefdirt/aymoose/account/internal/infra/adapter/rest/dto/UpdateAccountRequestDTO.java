package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto;

public record UpdateAccountRequestDTO(
        String phoneNumber,
        String firstName,
        String lastName) {

    public UpdateAccountRequestDTO(
            String phoneNumber,
            String firstName,
            String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}