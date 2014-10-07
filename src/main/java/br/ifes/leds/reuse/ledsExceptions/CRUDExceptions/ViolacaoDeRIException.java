package br.ifes.leds.reuse.ledsExceptions.CRUDExceptions;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author leds-6752
 */
public class ViolacaoDeRIException extends RuntimeException {

    public ViolacaoDeRIException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    @Getter
    private Set<? extends ConstraintViolation<?>> constraintViolations;

}
