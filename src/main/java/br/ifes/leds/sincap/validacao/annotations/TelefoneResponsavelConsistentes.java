package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.TelefoneResponsavelValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TelefoneResponsavelValidator.class})
@Documented
/**
 * Verifica os telefones do responsavel para os dois não sejam iguais
 */
public @interface TelefoneResponsavelConsistentes {

    String message() default "{br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
