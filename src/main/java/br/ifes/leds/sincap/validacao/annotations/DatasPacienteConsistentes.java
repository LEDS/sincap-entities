package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.DataPacienteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DataPacienteValidator.class})
@Documented
/**
 * Verifica de a data de internação é depois da data de nascimento
 */
public @interface DatasPacienteConsistentes {

    String message() default "{br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
