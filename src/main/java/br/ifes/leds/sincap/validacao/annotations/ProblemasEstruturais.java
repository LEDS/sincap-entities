package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.ProblemasEstruturaisValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProblemasEstruturaisValidator.class})
@Documented
public @interface ProblemasEstruturais {

    String message() default "{EntrevistaValida.problemasEstruturais}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
