package gtu.cse.se.altefdirt.aymoose.account.api.rest.dto;

import java.time.Instant;
import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateAccountRequestDTO(
       String username,
       String email,
       String password,
       String fullName,
       String profilePicture) 
    {   
        
    public CreateAccountRequestDTO (
        String username,
        String email,
        String password,
        String fullName,
        String profilePicture) {
        Validate.notNull(username, "Accountname cannot be null");
        Validate.notNull(email, "Email cannot be null");
        Validate.notNull(password, "Password cannot be null");
        Validate.notNull(fullName, "Full name cannot be null");
        Validate.isTrue(username.length() >= 3 && username.length() <= 80, "Accountname must be between 3 and 80 characters");
        Validate.isTrue(email.length() >= 3 && email.length() <= 80, "Email must be between 3 and 80 characters");
        Validate.isTrue(password.length() >= 3 && password.length() <= 80, "Password must be between 3 and 80 characters");
        Validate.isTrue(fullName.length() >= 3 && fullName.length() <= 80, "Full name must be between 3 and 80 characters");
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
    } 
}