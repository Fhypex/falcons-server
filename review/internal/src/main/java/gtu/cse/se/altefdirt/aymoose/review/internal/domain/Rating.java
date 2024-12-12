package gtu.cse.se.altefdirt.aymoose.review.internal.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@ValueObject
public record Rating(
        @NotNull @Max(5) @Min(1) Short leading, @NotNull @Max(9) @Min(0) Short decimal) {

    private static final String REGEX = "^[1-5](.[0-9])?$";

    @JsonValue
    public String value() {
        return leading + "." + decimal;
    }

    public static Rating valueOf(short leading, short decimal) {
        return new Rating(leading, decimal);
    }

    public static Rating fromShort(short number) {
        return new Rating(number, (short) 0);
    }

    public static Rating fromString(String rating) {
        if (!rating.matches(REGEX))
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        short numberPart = Short.parseShort(rating.split("\\.")[0]);
        short decimalPart = Short.parseShort(rating.split("\\.")[1]);
        return new Rating(numberPart, decimalPart);
    }

    public static Rating fromRound(String rating, boolean roundUp) {
        if (rating.matches(REGEX)) {
            short rounded = Short.parseShort(rating.split("\\.")[0]);
            if (roundUp) {
                if (rating.contains(".")) {
                    rounded++;
                }
            }
            return new Rating(rounded, (short) 0);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }
}
