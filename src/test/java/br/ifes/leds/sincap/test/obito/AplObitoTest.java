package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;
import br.ifes.leds.sincap.test.AbstractionTest;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AplObitoTest extends AbstractionTest {

    @Autowired
    private AplObito aplObito;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    @Autowired
    private ObitoTestUtil util;

    private ProcessoNotificacao processoComObitoValido;

    @Autowired
    private ObitoTestUtil obitoTestUtil;

    @Before
    public void before() throws Exception {
        processoComObitoValido = obitoTestUtil.processoComObitoValido();
    }

    @Test
    public void salvarNovoObitoPniNaoEncaminhadoAptoDadosValidos(){
        processoComObitoValido.getObito().setId(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(processoComObitoValido);
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);


        final ProcessoNotificacao notificacaoSalva = aplObito.salvarObito(notificacaoDTO);

        assertThat(notificacaoSalva, allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("causaNaoDoacao", isEmptyOrNullString()))
        );

        assertThat(notificacaoSalva.getObito().getPrimeiraCausaMortis(), allOf(
                        hasProperty("nome", is("Causa mortis 1")))
        );
    }

    @Test
    public void editarNovoObitoPniNaoEncaminhadoAptoDadosValidos(){
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setDocumento(null);
        processoComObitoValido.getObito().getPaciente().getDocumentoSocial().setTipoDocumentoComFoto(PNI);

        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(processoComObitoValido);
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);


        final ProcessoNotificacao notificacaoSalva = aplObito.salvarObito(notificacaoDTO);

        assertThat(notificacaoSalva, allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("causaNaoDoacao", isEmptyOrNullString()))
        );

        assertThat(notificacaoSalva.getObito().getPrimeiraCausaMortis(), allOf(
                        hasProperty("nome", is("Causa mortis 1")))
        );
    }


    @Test
    public void testVoid() throws Exception {
        assertTrue(true);
    }
}
