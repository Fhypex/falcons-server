package gtu.cse.se.altefdirt.aymoose.shared.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Constraint(validatedBy = AggregateIdValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAggregateId {
    String message() default "Invalid aggregate ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class AggregateIdValidator implements ConstraintValidator<ValidAggregateId, String> {
    @Override
    public boolean isValid(String reservationId, ConstraintValidatorContext context) {
        // Implement your custom validation logic
        return false;
        /* System.out.println("Validating aggregate ID: " + reservationId);
        return reservationId != null && !reservationId.isEmpty() && reservationId.length() == 36; */
    }
}