package gtu.cse.se.altefdirt.aymoose.account.api.rest.dto;

import java.time.Instant;
import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateAccountRequestDTO(
        String mailAddress,
        String password,
        String firstName,
        String lastName) {

    public CreateAccountRequestDTO(
            String mailAddress,
            String password,
            String firstName,
            String lastName) {
        Validate.notNull(mailAddress, "Email cannot be null");
        Validate.notNull(password, "Password cannot be null");
        Validate.notNull(firstName, "First name cannot be null");
        Validate.notNull(lastName, "Last name cannot be null");
        Validate.isTrue(mailAddress.length() >= 3 && mailAddress.length() <= 80,
                "Email must be between 3 and 80 characters");
        Validate.isTrue(password.length() >= 3 && password.length() <= 80,
                "Password must be between 3 and 80 characters");
        Validate.isTrue(firstName.length() >= 3 && firstName.length() <= 80,
                "First name must be between 3 and 80 characters");
        Validate.isTrue(lastName.length() >= 3 && lastName.length() <= 80,
                "Last name must be between 3 and 80 characters");
        this.mailAddress = mailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}