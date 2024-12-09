package gtu.cse.se.altefdirt.aymoose.shared.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FullName(
    @NotNull @Size(min = 3, max = 255) String firstName,
    @NotNull @Size(min = 3, max = 255) String lastName) { 
        
    public static FullName of(String firstName, String lastName) {
        return new FullName(firstName, lastName);
    }

    public String value() {
        return firstName + " " + lastName;
    }
}