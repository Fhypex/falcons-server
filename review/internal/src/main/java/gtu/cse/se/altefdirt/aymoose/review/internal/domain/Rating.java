package gtu.cse.se.altefdirt.aymoose.review.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SingleValueObject;
import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@ValueObject
public record Rating(
    @NotNull @Max(5) @Min(1) Short rating) implements SingleValueObject<Short> {    

    private static final String REGEX = "^[1-5](.[0-9])?$";

    @Override
    public Short value() {
        return rating;
    }

    public static Rating valueOf(short rating) {
        return new Rating(rating);
    }

    public static Rating fromRound(String rating, boolean roundUp) {
        if(rating.matches(REGEX)) {
            short rounded = Short.parseShort(rating.split("\\.")[0]);
            if(roundUp) {
                if(rating.contains(".")) {
                    rounded++;
                }
            }
            return new Rating(rounded);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }
}
