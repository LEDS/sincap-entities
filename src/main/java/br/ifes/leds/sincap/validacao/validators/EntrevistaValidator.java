package br.ifes.leds.sincap.validacao.validators;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.validacao.annotations.EntrevistaValida;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;
import static br.ifes.leds.sincap.validacao.validators.EntrevistaValidator.ValidacaoEntrevistaBuilder.validacao;

public class EntrevistaValidator implements ConstraintValidator<EntrevistaValida, ProcessoNotificacao> {

    @Override
    public void initialize(EntrevistaValida constraintAnnotation) {

    }

    @Override
    public boolean isValid(ProcessoNotificacao processo, ConstraintValidatorContext context) {
        boolean isValid = processo.getEntrevista() == null;

        if (processo.getEntrevista() != null) {
            isValid = true;

            if (processo.getId() == null) {
                context.buildConstraintViolationWithTemplate("{EntrevistaValida.processoSemId}").addConstraintViolation();
                isValid = false;
            }

            if (entrevistaNaoRealizada(processo)) {
                isValid = isValid && validacao(context)
                        .condicao(temProblemasEstruturais(processo), "EntrevistaValida.problemasEstruturais", "Causa Não doação")
                        .condicao(naoTemDataEntrevista(processo), "EntrevistaValida.dataEntrevista", "Data de entrevista")
                        .condicao(processo.getEntrevista().getResponsavel() == null, "EntrevistaValida.responsavel1", "Responsável 1")
                        .condicao(processo.getEntrevista().getResponsavel2() == null, "EntrevistaValida.responsavel2", "Responsável 2")
                        .condicao(processo.getEntrevista().getTestemunha1() == null, "EntrevistaValida.testemunha1", "Testemunha 1")
                        .condicao(processo.getEntrevista().getTestemunha2() == null, "EntrevistaValida.testemunha2", "Testemunha 2")
                        .build();
            }
        }

        return isValid;
    }

    private boolean naoTemDataEntrevista(ProcessoNotificacao processo) {
        return processo.getEntrevista().getDataEntrevista() == null;
    }

    private static boolean temProblemasEstruturais(ProcessoNotificacao processo) {
        return processo.getCausaNaoDoacao() != null &&  processo.getCausaNaoDoacao().getTipoNaoDoacao() == PROBLEMAS_ESTRUTURAIS;
    }

    static class ValidacaoEntrevistaBuilder {
        private final ConstraintValidatorContext context;
        private boolean isValid = true;

        public ValidacaoEntrevistaBuilder(ConstraintValidatorContext context) {
            this.context = context;
        }

        public static ValidacaoEntrevistaBuilder validacao(ConstraintValidatorContext context) {
            return new ValidacaoEntrevistaBuilder(context);
        }

        public ValidacaoEntrevistaBuilder condicao(boolean condicao, String template, String property) {
            this.isValid = this.isValid && condicao;

            if (!condicao)
                context.buildConstraintViolationWithTemplate("{" + template + "}").addPropertyNode(property).addConstraintViolation();

            return this;
        }

        public boolean build() {
            return this.isValid;
        }
    }

    private boolean entrevistaNaoRealizada(ProcessoNotificacao processo) {
        return processo.getEntrevista() != null && !processo.getEntrevista().isEntrevistaRealizada();
    }

}
