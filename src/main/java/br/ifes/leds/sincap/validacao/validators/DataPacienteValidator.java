package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.PacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataPacienteValidator implements ConstraintValidator<DatasPacienteConsistentes, PacienteInterface> {

    @Override
    public void initialize(DatasPacienteConsistentes datasPacienteConsistentes) {
    }

    @Override
    public boolean isValid(PacienteInterface paciente, ConstraintValidatorContext constraintValidatorContext) {
        return temAlgumNull(paciente) || dataInternacaoMaiorOuIgualDataNascimento(paciente);
    }

    private static boolean dataInternacaoMaiorOuIgualDataNascimento(PacienteInterface paciente) {
        return paciente.getDataInternacao().compareTo(paciente.getDataNascimento()) >= 0;
    }

    private static boolean temAlgumNull(PacienteInterface paciente) {
        return paciente == null || paciente.getDataInternacao() == null || paciente.getDataNascimento() == null;
    }
}
