package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TelefonesResponsavelInterface;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelefoneResponsavelValidator implements ConstraintValidator<TelefoneResponsavelConsistentes, TelefonesResponsavelInterface> {

    @Override
    public void initialize(TelefoneResponsavelConsistentes telefoneResponsavelConsistentes) {
    }

    @Override
    public boolean isValid(TelefonesResponsavelInterface responsavel, ConstraintValidatorContext constraintValidatorContext) {
        return !responsavel.getTelefone().equals(responsavel.getTelefone2());
    }
}
