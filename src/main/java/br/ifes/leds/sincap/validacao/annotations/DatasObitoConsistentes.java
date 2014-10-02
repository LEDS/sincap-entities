package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.DatasObitoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DatasObitoValidator.class})
@Documented
/**
 * Verifica se a data de óbito é mais recente ou igual à data de internação.
 */
public @interface DatasObitoConsistentes {

    String message() default "{br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
