package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplCausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;
import br.ifes.leds.sincap.test.AbstractionTest;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento.SVO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.RG;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.CONTRAINDICACAO_MEDICA;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;
import static br.ifes.leds.sincap.test.TestUtil.haVinteAnos;
import static br.ifes.leds.sincap.test.TestUtil.nao;
import static br.ifes.leds.sincap.test.obito.ObitoTestUtil.causaNaoDoacao;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AplObitoTest extends AbstractionTest {

    @Autowired
    private AplCausaNaoDoacao aplCausaNaoDoacao;
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

    private CausaNaoDoacao causaNaoDoacao;

    @Before
    public void before() throws Exception {
        processoComObitoValido = obitoTestUtil.processoComObitoValido();
        causaNaoDoacao = causaNaoDoacao();
        aplCausaNaoDoacao.cadastrar(causaNaoDoacao);
    }

    @Test
    public void salvarNovoObitoPniNaoEncaminhadoApto(){
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
    public void editarNovoObitoPniNaoEncaminhadoApto(){
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
    public void salvarObitoNaoPniNaoEncaminhadoApto(){

        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(processoComObitoValido);
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);


        final ProcessoNotificacao notificacaoSalva = aplObito.salvarObito(notificacaoDTO);

        assertThat(notificacaoSalva, allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("causaNaoDoacao", isEmptyOrNullString()))
        );

        assertThat(notificacaoSalva.getObito().getPaciente(), allOf(
                        hasProperty("nome", is("Paciente Obito 1")),
                        hasProperty("dataNascimento", notNullValue()),
                        hasProperty("nomeMae", is("Nome da Mãe")),
                        hasProperty("numeroSUS", is("123456789")))
        );

        assertThat(notificacaoSalva.getObito().getPaciente().getDocumentoSocial(), allOf(
                        hasProperty("documento", is("2131432")),
                        hasProperty("tipoDocumentoComFoto", is(RG)))
        );

        assertThat(notificacaoSalva.getObito().getPrimeiraCausaMortis(), allOf(
                        hasProperty("nome", is("Causa mortis 1")))
        );
    }

    @Test
    public void salvarObitoNaoPniEncaminhadoNaoApto(){

        processoComObitoValido.getObito().setAptoDoacao(nao);
        processoComObitoValido.getObito().setCorpoEncaminhamento(SVO);
        processoComObitoValido.setCausaNaoDoacao(causaNaoDoacao);

        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(processoComObitoValido);
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);


        final ProcessoNotificacao notificacaoSalva = aplObito.salvarObito(notificacaoDTO);

        assertThat(notificacaoSalva, allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("causaNaoDoacao", notNullValue()))
        );

        assertThat(notificacaoSalva.getCausaNaoDoacao(), allOf(
                        hasProperty("nome", is("Causa não doação 1")),
                        hasProperty("tipoNaoDoacao", is(CONTRAINDICACAO_MEDICA)))
        );
    }

    @Test
    public void testVoid() throws Exception {
        assertTrue(true);
    }
}
