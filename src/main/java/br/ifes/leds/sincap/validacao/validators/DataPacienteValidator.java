package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.DatasPacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataPacienteValidator implements ConstraintValidator<DatasPacienteConsistentes, DatasPacienteInterface> {

    @Override
    public void initialize(DatasPacienteConsistentes datasPacienteConsistentes) {
    }

    @Override
    public boolean isValid(DatasPacienteInterface paciente, ConstraintValidatorContext constraintValidatorContext) {
        return paciente == null || paciente.getDataInternacao() == null || paciente.getDataNascimento() == null ||
                paciente.getDataInternacao().compareTo(paciente.getDataNascimento()) >= 0;
    }
}
