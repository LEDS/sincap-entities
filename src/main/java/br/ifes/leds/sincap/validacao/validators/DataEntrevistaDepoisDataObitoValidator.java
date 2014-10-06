package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ProcessoNotificacaoInterface;
import br.ifes.leds.sincap.validacao.annotations.DataEntrevistaObitoConsistentes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataEntrevistaDepoisDataObitoValidator implements ConstraintValidator<DataEntrevistaObitoConsistentes, ProcessoNotificacaoInterface> {

    @Override
    public void initialize(DataEntrevistaObitoConsistentes dataEntrevistaObitoConsistentes) {

    }

    @Override
    public boolean isValid(ProcessoNotificacaoInterface processo, ConstraintValidatorContext constraintValidatorContext) {
        return temAlgumNull(processo) || dataEntrevistaMaiorOuIgualDataObito(processo);
    }

    private static boolean dataEntrevistaMaiorOuIgualDataObito(ProcessoNotificacaoInterface processo) {
        return processo.getEntrevista().getDataEntrevista().compareTo(processo.getObito().getDataObito()) >= 0;
    }

    private static boolean temAlgumNull(ProcessoNotificacaoInterface processo) {
        return processo == null || processo.getObito() == null || processo.getEntrevista() == null
                || processo.getObito().getDataObito() == null || processo.getEntrevista().getDataEntrevista() == null;
    }
}
