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
        return temAlgumNull(responsavel) || telefone1DiferenteDeTelefone2(responsavel);
    }

    private static boolean telefone1DiferenteDeTelefone2(TelefonesResponsavelInterface responsavel) {
        return !responsavel.getTelefone().equals(responsavel.getTelefone2());
    }

    private static boolean temAlgumNull(TelefonesResponsavelInterface responsavel) {
        return responsavel == null || responsavel.getTelefone() == null || responsavel.getTelefone2() == null;
    }
}
