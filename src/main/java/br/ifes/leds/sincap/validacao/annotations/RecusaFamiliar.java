package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.ProblemasEstruturaisValidator;
import br.ifes.leds.sincap.validacao.validators.RecusaFamiliarValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RecusaFamiliarValidator.class})
@Documented
public @interface RecusaFamiliar {

    String message() default "{EntrevistaValida.recusaFamiliar}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
