package gtu.cse.se.altefdirt.aymoose.shared.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
    }

    public boolean isToday() {
        return date.isEqual(LocalDate.now());
    }

    public boolean isBeforeToday() {
        return date.isBefore(LocalDate.now());
    }

    public static Integer currentHour() {
        return LocalTime.now().getHour();
    }

    @JsonCreator
    public static Date fromString(String value) {
        LocalDate parsedDate = LocalDate.parse(value, FORMATTER);
        return new Date(parsedDate);
    }

    public static Date fromLocalDate(LocalDate date) {
        return new Date(date);
    }

    public Date plusDays(int days) {
        return new Date(date.plusDays(days));
    }

    @JsonValue
    public String asString() {
        return date.format(FORMATTER);
    }

    @Override
    public String toString() {
        return asString();
    }

    public LocalDate localValue() {
        return date;
    }

    // Optionally, provide a method to get the LocalDate
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    public static class DateSerializer extends com.fasterxml.jackson.databind.JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, com.fasterxml.jackson.core.JsonGenerator gen,
                com.fasterxml.jackson.databind.SerializerProvider serializers) throws java.io.IOException {
            gen.writeString(value.format(FORMATTER));
        }
    }

    public static class DateDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(com.fasterxml.jackson.core.JsonParser p,
                com.fasterxml.jackson.databind.DeserializationContext ctxt) throws java.io.IOException {
            return LocalDate.parse(p.getText(), FORMATTER);
        }
    }
}