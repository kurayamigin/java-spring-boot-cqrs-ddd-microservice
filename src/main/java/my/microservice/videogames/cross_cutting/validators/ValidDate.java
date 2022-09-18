package my.microservice.videogames.cross_cutting.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidation.class)
public @interface ValidDate {
    String message() default "not a valid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class ValidDateValidation implements ConstraintValidator<ValidDate, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return false;
        Date today = Date.from(Instant.now());
        return date.equals(today) || date.before(today);
    }
}
