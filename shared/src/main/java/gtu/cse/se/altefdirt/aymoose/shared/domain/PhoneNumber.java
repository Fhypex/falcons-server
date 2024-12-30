package gtu.cse.se.altefdirt.aymoose.shared.domain;

public record PhoneNumber(
        String phoneNumber) implements SingleValueObject<String> {

    private static final long serialVersionUID = 1L;

    private static final PhoneNumber EMPTY = new PhoneNumber("");

    public String value() {
        return phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public static PhoneNumber of(String phoneNumber, String countryCode) {
        return new PhoneNumber(phoneNumber);
    }

    public static PhoneNumber empty() {
        return EMPTY;
    }
}