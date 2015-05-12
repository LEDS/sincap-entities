package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.test.AbstractionTest;
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
    public void test_validacao_novo_obito_pni_nao_encaminhado_apto_dados_validos() throws Exception {
        final ProcessoNotificacao obito = obitoTestUtil.processoComObitoValido();

        obito.getObito().setDataObito(haDuasHoras());
        obito.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        obito.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(obito);

        assertThat("Óbito não possui violações", violations, empty());
    }
}
