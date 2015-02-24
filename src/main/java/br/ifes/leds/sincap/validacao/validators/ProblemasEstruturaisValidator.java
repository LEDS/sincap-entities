package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.validacao.annotations.ProblemasEstruturais;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;

public class ProblemasEstruturaisValidator implements ConstraintValidator<ProblemasEstruturais, TipoNaoDoacao> {

    @Override
    public void initialize(ProblemasEstruturais constraintAnnotation) {

    }

    @Override
    public boolean isValid(TipoNaoDoacao value, ConstraintValidatorContext context) {
        return value == PROBLEMAS_ESTRUTURAIS;
    }
}
