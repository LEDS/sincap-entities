package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.validacao.annotations.RecusaFamiliar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.RECUSA_FAMILIAR;

public class RecusaFamiliarValidator implements ConstraintValidator<RecusaFamiliar, TipoNaoDoacao> {

    @Override
    public void initialize(RecusaFamiliar constraintAnnotation) {

    }

    @Override
    public boolean isValid(TipoNaoDoacao value, ConstraintValidatorContext context) {
        return value == RECUSA_FAMILIAR;
    }
}
