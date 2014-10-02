package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TemDatasPacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DatasObitoValidator implements ConstraintValidator<DatasObitoConsistentes, TemDatasPacienteInterface> {

    @Override
    public void initialize(DatasObitoConsistentes datasObitoConsistentes) {

    }

    @Override
    public boolean isValid(TemDatasPacienteInterface obito, ConstraintValidatorContext constraintValidatorContext) {
        return temAlgumNull(obito) || dataObitoMaiorOuIgualDataInternacao(obito);
    }

    private static boolean dataObitoMaiorOuIgualDataInternacao(TemDatasPacienteInterface obito) {
        return obito.getDataObito().compareTo(obito.getPaciente().getDataInternacao()) >= 0;
    }

    private static boolean temAlgumNull(TemDatasPacienteInterface obito) {
        return obito == null || obito.getPaciente() == null || obito.getDataObito() == null || obito.getPaciente().getDataInternacao() == null;
    }
}
