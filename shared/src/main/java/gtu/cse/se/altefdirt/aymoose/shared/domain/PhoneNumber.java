package gtu.cse.se.altefdirt.aymoose.shared.domain;

public record PhoneNumber(
                String phoneNumber) implements SingleValueObject<String> {

    public String value() {
        return phoneNumber;
    }
}