package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.test.AbstractionTest;
import br.ifes.leds.sincap.validacao.groups.obito.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento.NAO_ENCAMINHADO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;
import static br.ifes.leds.sincap.test.TestUtil.*;
import static br.ifes.leds.sincap.test.obito.ObitoTestUtil.causaNaoDoacao;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ObitoValidatorTest extends AbstractionTest {

    private static Validator validator;

    @Autowired
    private ObitoTestUtil obitoTestUtil;

    private ProcessoNotificacao processoComObitoValido;

    @BeforeClass
    public static void setUp() throws Exception {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void before() throws Exception {
        processoComObitoValido = obitoTestUtil.processoComObitoValido();
    }

    @Test
    public void testValidacaoNovoObitoPniNaoEncaminhadoAptoDadosValidos() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, NovaNotificacao.class);

        assertThat("Óbito não possui violações", violations, empty());
    }

    @Test
    public void testValidacaoNovoObitoPniNaoEncaminhadoAptoIdProcessoPreenchido() throws Exception {
        processoComObitoValido.setId(id());
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, NovaNotificacao.class);

        assertThat("Id do processo está preenchido quando não deveria",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Mensagem de erro está errada",
                temErro(violations, "Id do processo de notificação está preenchida para uma nova notificação."), is(true));
    }

    @Test
    public void testValidacaoObitoPniNaoEncaminhadoAptoIdProcessoNaoPreenchido() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, NotificacaoSalva.class);

        assertThat("Atualização de processo sem id!",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Mensagem de erro sobre atualização de processo sem id está errada",
                temErro(violations, "Processo sem ID"), is(true));
    }

    @Test
    public void testValidacaoObitoPniEncaminhadoApto() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, ObitoEncaminhado.class);

        assertThat("Primeira causa de Óbito não é obrigatória.",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Quando um obito é encaminhado a primeira causa não é obrigatória.",
                temErro(violations, "Primeira causa não é obrigatória."), is(true));
    }

    @Test
    public void testValidacaoObitoPniNaoEncaminhadoApto() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);
        processoComObitoValido.getObito().setCorpoEncaminhamento(NAO_ENCAMINHADO);
        processoComObitoValido.getObito().setPrimeiraCausaMortis(null);
        processoComObitoValido.getObito().setSegundaCausaMortis(null);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, ObitoNaoEncaminhado.class);

        assertThat("Primeira causa de Óbito é obrigatória.",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Quando um obito  não é encaminhado a 1ª causa é obrigatória.",
                temErro(violations, "Primeira causa é obrigatória."), is(true));
    }

    @Test
    public void testValidacaoObitoApto() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);
        processoComObitoValido.setCausaNaoDoacao(causaNaoDoacao());

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, ObitoApto.class);

        assertThat("Paciente apto não possui contra indicação médica.",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Quando um paciente é apto, não possui contra indicação médica.",
                temErro(violations, "Paciente apto com contra indicação médica."), is(true));
    }

    @Test
    public void testValidacaoObitoInapto() throws Exception {
        processoComObitoValido.getObito().setDataObito(haDuasHoras());
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoComObitoValido, ObitoInapto.class);

        assertThat("Paciente inapto deve ter uma contra indicação médica.",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Quando um paciente é inapto, deve ter uma contra indicação médica.",
                temErro(violations, "Paciente inapto sem contra indicação médica."), is(true));
    }
}
