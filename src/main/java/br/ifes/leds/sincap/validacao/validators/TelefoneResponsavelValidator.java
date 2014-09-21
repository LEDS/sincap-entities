package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelefoneResponsavelValidator implements ConstraintValidator<TelefoneResponsavelConsistentes, Responsavel> {

    @Override
    public void initialize(TelefoneResponsavelConsistentes telefoneResponsavelConsistentes) {
    }

    @Override
    public boolean isValid(Responsavel responsavel, ConstraintValidatorContext constraintValidatorContext) {
        return !responsavel.getTelefone().equals(responsavel.getTelefone2());
    }
}
