package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.test.AbstractionTest;
import br.ifes.leds.sincap.validacao.groups.obito.NovaNotificacao;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;
import static br.ifes.leds.sincap.test.TestUtil.haDuasHoras;
import static br.ifes.leds.sincap.test.TestUtil.id;
import static br.ifes.leds.sincap.test.TestUtil.temErro;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ObitoValidatorTest extends AbstractionTest {

    private static Validator validator;

    @Autowired
    private ObitoTestUtil obitoTestUtil;

    @BeforeClass
    public static void setUp() throws Exception {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidacaoNovoObitoPniNaoEncaminhadoAptoDadosValidos() throws Exception {
        final ProcessoNotificacao obito = obitoTestUtil.processoComObitoValido();

        obito.getObito().setDataObito(haDuasHoras());
        obito.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        obito.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(obito, NovaNotificacao.class);

        assertThat("Óbito não possui violações", violations, empty());
    }

    @Test
    public void testValidacaoNovoObitoPniNaoEncaminhadoAptoIdProcessoPreenchido() throws Exception {
        final ProcessoNotificacao obito = obitoTestUtil.processoComObitoValido();

        obito.setId(id());
        obito.getObito().setDataObito(haDuasHoras());
        obito.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        obito.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(obito, NovaNotificacao.class);

        assertThat("Id do processo está preenchido quando não deveria",
                violations.size(), greaterThanOrEqualTo(1));

        assertThat("Mensagem de erro está errada",
                temErro(violations, "Id do processo de notificação está preenchida para uma nova notificação."), is(true));
    }
}
