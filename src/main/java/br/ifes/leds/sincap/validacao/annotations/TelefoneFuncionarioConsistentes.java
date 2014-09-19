package br.ifes.leds.sincap.validacao.annotations;

import br.ifes.leds.sincap.validacao.validators.TelefoneFuncionarioValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TelefoneFuncionarioValidator.class})
@Documented
/**
 * Verifica o telefone do funcionario, se o mesmo existe
 */
public @interface TelefoneFuncionarioConsistentes {

    String message() default "{br.ifes.leds.sincap.validacao.annotations.TelefoneFuncionarioConsistentes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
