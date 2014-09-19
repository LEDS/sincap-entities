package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.validacao.annotations.TelefoneFuncionarioConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelefoneFuncionarioValidator implements ConstraintValidator<TelefoneFuncionarioConsistentes, Funcionario> {

    @Override
    public void initialize(TelefoneFuncionarioConsistentes telefoneFuncionarioConsistentes) {
    }

    @Override
    public boolean isValid(Funcionario funcionario, ConstraintValidatorContext constraintValidatorContext) {
        return funcionario.getTelefone() != null;
    }
}
