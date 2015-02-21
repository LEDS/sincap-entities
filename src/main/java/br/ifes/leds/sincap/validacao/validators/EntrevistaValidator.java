package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.validacao.annotations.EntrevistaValida;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;

public class EntrevistaValidator implements ConstraintValidator<EntrevistaValida, ProcessoNotificacao> {

    @Override
    public void initialize(EntrevistaValida constraintAnnotation) {

    }

    @Override
    public boolean isValid(ProcessoNotificacao processo, ConstraintValidatorContext context) {
        boolean isValid = processo.getEntrevista() == null;
        
        if (processo.getId() == null) {
            context.buildConstraintViolationWithTemplate("{EntrevistaValida.processoSemId}").addConstraintViolation();
            isValid = false;
        }

        if (entrevistaNaoRealizada(processo)) {
            isValid = causaNaoDoacaoProblemasEstruturais(processo, context)
                    && processo.getEntrevista().getDataEntrevista() == null
                    && processo.getEntrevista().getResponsavel() == null
                    && processo.getEntrevista().getResponsavel2() == null
                    && processo.getEntrevista().getTestemunha1() == null
                    && processo.getEntrevista().getTestemunha2() == null;
        }

        return isValid;
    }

    private boolean causaNaoDoacaoProblemasEstruturais(ProcessoNotificacao processo, ConstraintValidatorContext context) {
        boolean isValid = processo.getCausaNaoDoacao() != null &&  processo.getCausaNaoDoacao().getTipoNaoDoacao() == PROBLEMAS_ESTRUTURAIS;
        if (!isValid) {
            context.buildConstraintViolationWithTemplate("{EntrevistaValida.problemasEstruturais}").addConstraintViolation();
        }

        return isValid;
    }

    private boolean entrevistaNaoRealizada(ProcessoNotificacao processo) {
        return processo.getEntrevista() != null && !processo.getEntrevista().isEntrevistaRealizada();
    }

}
