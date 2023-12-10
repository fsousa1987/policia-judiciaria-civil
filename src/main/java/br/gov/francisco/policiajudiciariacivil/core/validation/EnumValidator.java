package br.gov.francisco.policiajudiciariacivil.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorConstraint.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClassName();

    String message() default "Sexo inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}