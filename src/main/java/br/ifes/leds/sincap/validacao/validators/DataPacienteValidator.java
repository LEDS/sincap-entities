package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataPacienteValidator implements ConstraintValidator<DatasPacienteConsistentes, Paciente> {

    @Override
    public void initialize(DatasPacienteConsistentes datasPacienteConsistentes) {
    }

    @Override
    public boolean isValid(Paciente paciente, ConstraintValidatorContext constraintValidatorContext) {
        return paciente == null || paciente.getDataInternacao().compareTo(paciente.getDataNascimento()) >= 0;
    }
}
