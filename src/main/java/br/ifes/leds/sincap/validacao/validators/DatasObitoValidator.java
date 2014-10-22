package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ObitoInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DatasObitoValidator implements ConstraintValidator<DatasObitoConsistentes, ObitoInterface> {

    @Override
    public void initialize(DatasObitoConsistentes datasObitoConsistentes) {

    }

    @Override
    public boolean isValid(ObitoInterface obito, ConstraintValidatorContext constraintValidatorContext) {
        return temAlgumNull(obito) || dataObitoMaiorOuIgualDataInternacao(obito);
    }

    private static boolean dataObitoMaiorOuIgualDataInternacao(ObitoInterface obito) {
        return obito.getDataObito().compareTo(obito.getPaciente().getDataInternacao()) >= 0;
    }

    private static boolean temAlgumNull(ObitoInterface obito) {
        return obito == null || obito.getPaciente() == null || obito.getDataObito() == null || obito.getPaciente().getDataInternacao() == null;
    }
}
