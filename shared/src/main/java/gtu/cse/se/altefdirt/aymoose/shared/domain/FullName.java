package gtu.cse.se.altefdirt.aymoose.shared.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FullName(
    @NotNull @Size(min = 3, max = 255) String fullName) implements SingleValueObject<String> { 
        
    public static FullName of(String value) {
        return new FullName(value);
    }

    public static FullName of(String name, String surname) {
        return new FullName(name + " " + surname);
    }

    @Override
    public String value() {
        return fullName;
    }
}