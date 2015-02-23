package br.ifes.leds.sincap.test.entrevista;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;
import br.ifes.leds.sincap.test.AbstractionTest;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaPacienteMenorIdade;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.*;
import static br.ifes.leds.sincap.test.entrevista.EntrevistaTestUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class EntrevistaValidatorTest extends AbstractionTest {

    private static Validator validator;

    @Autowired
    private EntrevistaTestUtil util;
    private ProcessoNotificacao processoNotificacao;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void before() {
        this.processoNotificacao = util.processoComObitoValido();
    }

    @Test
    public void processoSemIdComEntrevistaValida() {
        processoNotificacao.setEntrevista(util.entrevistaNaoRealizada());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(PROBLEMAS_ESTRUTURAIS);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);
        boolean processoComId = sim;

        for (ConstraintViolation<ProcessoNotificacao> violation : violations) {
            if (violation.getMessage().equals("Processo sem ID"))
                processoComId = nao;
        }

        assertFalse("Processo sem ID está sendo válido", processoComId);
    }

    @Test
    public void processoSemIdSemEntrevista() {
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void processoComId() {
        processoNotificacao.setId(1L);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);
        boolean processoComId = sim;

        for (ConstraintViolation<ProcessoNotificacao> violation : violations) {
            if (violation.getMessage().equals("Processo sem ID"))
                processoComId = nao;
        }

        assertTrue("Processo com ID está sendo inválido", processoComId);
    }

    @Test
    public void processoComEntrevistaNull() {
        processoNotificacao.setId(1L);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);

        assertEquals(0, violations.size());
    }

    @Test
    public void entrevistaNaoRealizadaValida() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaNaoRealizada());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(PROBLEMAS_ESTRUTURAIS);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);

        assertEquals("Entrevista validando quando não deveria.", 0, violations.size());
    }

    @Test
    public void entrevistaNaoRealizadaSemCausaNaoDoacao() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaNaoRealizada());
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);
        final boolean temErro = temErro(violations, "Causa da entrevista não ter sido realizada não informada");

        assertTrue("Causa de não doação não validando.", temErro);
    }

    @Test
    public void entrevistaNaoRealizadaComCausaNaoDoacaoErrada() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaNaoRealizada());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(RECUSA_FAMILIAR);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);
        final boolean temErro = temErro(violations, "Causa da entrevista não ter sido realizada não informada");

        assertTrue(temErro);
    }

    @Test
    public void entrevistaNaoRealizadaComAMais() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaNaoRealizada());
        processoNotificacao.getEntrevista().setDataEntrevista(hoje());
        processoNotificacao.getEntrevista().setResponsavel(new Responsavel());
        processoNotificacao.getEntrevista().setResponsavel2(new Responsavel());
        processoNotificacao.getEntrevista().setTestemunha1(new Testemunha());
        processoNotificacao.getEntrevista().setTestemunha2(new Testemunha());
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);
        final boolean temErro = temErro(violations, "Campo testemunha 1 está preenchido");

        assertTrue("Validação da entrevista não realizada com dados a mais está errada.", temErro);
    }

    @Test
    public void entrevistaRealizadaComDadosPaciente() {
        processoNotificacao.setId(1L);
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(RECUSA_FAMILIAR);
        processoNotificacao.setEntrevista(util.entrevistaRealizada());
        processoNotificacao.getEntrevista().setDoacaoAutorizada(nao);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoNaoAutorizada.class);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void entrevistaRealizadaPacienteNull() {
        processoNotificacao.setId(1L);
        processoNotificacao.getObito().setPaciente(null);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoNaoAutorizada.class);
        final boolean temErro = temErro(violations, "Entrevista realizada sem os dados do paciente");

        assertTrue(temErro);
    }

    @Test
    public void entrevistaRealizadaPacienteDadosFaltando() {
        processoNotificacao.setId(1L);
        processoNotificacao.getObito().getPaciente().setDataNascimento(null);
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(RECUSA_FAMILIAR);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaNaoRealizada.class);

        assertEquals(1, violations.size());
    }

    @Test
    public void entrevistaRealizadaDoacaoNaoAutorizadaCausaNaoDoacaoErrada() {
        processoNotificacao.setId(id());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(PROBLEMAS_ESTRUTURAIS);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoNaoAutorizada.class);
        final boolean temErro = temErro(violations, "Causa de recusa familiar não informada");

        assertTrue(temErro);
    }

    @Test
    public void entrevistaRealizadaDoacaoAutorizadaCausaNaoDoacaoExiste() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaDoacaoAutorizada());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(CONTRAINDICACAO_MEDICA);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoAutorizada.class);
        final boolean temErro = temErro(violations, "Causa de não doação está definida mesmo com doação autorizada");

        assertTrue(temErro);
    }

    @Test
    public void entrevistaRealizadaDoacaoAutorizadaCausaNaoDoacaoNull() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(util.entrevistaDoacaoAutorizada());

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoAutorizada.class);
        final boolean temErro = temErro(violations, "Causa de não doação está definida mesmo com doação autorizada");

        assertFalse("Entrevista tem erro de causa de não doação quando não deveria", temErro);
    }

    @Test
    public void entrevistaRealizadaDoacaoAutorizadaResponsavelNull() {
        processoNotificacao.setId(id());
        processoNotificacao.setEntrevista(util.entrevistaDoacaoAutorizada());
        processoNotificacao.getEntrevista().setResponsavel(null);
        processoNotificacao.getEntrevista().setTestemunha1(null);
        processoNotificacao.getEntrevista().setTestemunha2(null);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoAutorizada.class);
        final boolean responsavel1 = temErro(violations, "Dados do responsável 1 não preenchidos");
        final boolean testemunha1 = temErro(violations, "Dados da testemunha 1 não preenchidos");
        final boolean testemunha2 = temErro(violations, "Dados da testemunha 2 não preenchidos");


        assertEquals(3, violations.size());
        assertTrue(responsavel1);
        assertTrue(testemunha1);
        assertTrue(testemunha2);
    }

    @Test
    public void entrevistaRealizadaDoacaoAutorizadaResponsavelIncompleto() {
        processoNotificacao.setId(id());
        processoNotificacao.setEntrevista(util.entrevistaDoacaoAutorizada());
        processoNotificacao.getEntrevista().setResponsavel(new Responsavel());
        processoNotificacao.getEntrevista().setTestemunha1(new Testemunha());
        processoNotificacao.getEntrevista().setTestemunha2(new Testemunha());

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaRealizadaDoacaoAutorizada.class);

        assertThat(
                "Doação autorizada não validando os responsáveis e testemunhas",
                violations,
                hasItem(Matchers.<ConstraintViolation<ProcessoNotificacao>>hasProperty("message", is("Não pode ser nulo")))
        );
    }

    @Test
    public void entrevistaRealizadaDoacaoAutorizadaMenorDeIdade() {
        processoNotificacao.setId(id());
        processoNotificacao.getObito().getPaciente().setDataNascimento(dezesseisAnos());
        processoNotificacao.setEntrevista(util.entrevistaDoacaoAutorizada());
        processoNotificacao.getEntrevista().setResponsavel(util.responsavel());
        processoNotificacao.getEntrevista().setTestemunha1(util.testemunha());
        processoNotificacao.getEntrevista().setTestemunha2(util.testemunha());

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao, EntrevistaPacienteMenorIdade.class);

        assertThat(
                "Não validando responsável 2 quando paciente é menor de idade",
                violations,
                contains(hasProperty("message", is("Paciente menor de idade precisa de dois responsáveis")))
        );
    }

    static boolean temErro(Set<ConstraintViolation<ProcessoNotificacao>> violations, String msgErro) {
        boolean temErro = nao;
        for (ConstraintViolation<ProcessoNotificacao> violation : violations) {
            if (violation.getMessage().equals(msgErro))
                temErro = sim;
        }
        return temErro;
    }

}
