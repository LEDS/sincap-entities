package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.DataEntrevistaDepoisDataObitoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DataEntrevistaDepoisDataObitoValidator.class})
@Documented
public @interface DataEntrevistaObitoConsistentes {

    String message() default "{br.ifes.leds.sincap.validacao.annotations.DataEntrevistaObitoConsistentes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
