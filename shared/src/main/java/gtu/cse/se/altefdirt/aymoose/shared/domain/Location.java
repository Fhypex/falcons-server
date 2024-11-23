package gtu.cse.se.altefdirt.aymoose.shared.domain;

public record Location(
    String location) implements SingleValueObject<String>
{
    public Location {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
    }

    @Override
    public String value() {
        return location;
    }
}
