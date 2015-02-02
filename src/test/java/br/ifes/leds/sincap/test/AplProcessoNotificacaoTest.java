package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.*;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author 20102BSI0553
 */
public class AplProcessoNotificacaoTest extends AbstractionTest {

    @Autowired
    private AplProcessoNotificacao aplProcessoNotificacao;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private NotificadorRepository notificadorRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private CausaNaoDoacaoRepository causaNaoDoacaoRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private PacienteData pacienteData;
    @Autowired
    private ObitoData obitoData;
    @Autowired
    private EntrevistaData entrevistaData;
    @Autowired
    private CaptacaoData captacaoData;
    @Autowired
    private CaptadorData captadorData;
    @Autowired
    private DataFactory df;

    private ProcessoNotificacaoDTO notificacao;

    @Before
    public void before() {
        notificacao = new ProcessoNotificacaoDTO();

        this.notificacao.setDataAbertura(Calendar.getInstance());
        this.notificacao.setArquivado(false);

        this.getEstadoNotificacao(this.notificacao);
        this.getObito(this.notificacao);

        Notificador notificador = notificadorRepository.findAll().get(0);
        this.notificacao.setNotificador(notificador.getId());
    }

    private void getEstadoNotificacao(ProcessoNotificacaoDTO notificacao) {
        Notificador notificador = notificadorRepository.findAll().get(0);

        ProcessoNotificacao processoNotificacao = mapper.map(notificacao, ProcessoNotificacao.class);


        AtualizacaoEstado novoEstado = new AtualizacaoEstado();
        novoEstado.setFuncionario(notificador);
        novoEstado
                .setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO);
        novoEstado.setDataAtualizacaos(Calendar.getInstance());
        processoNotificacao.mudarEstadoAtual(novoEstado);
    }

    private void getObito(ProcessoNotificacaoDTO notificacao) {
        notificacao.setObito(new ObitoDTO());
        ObitoDTO obito = notificacao.getObito();
        Setor setor = setorRepository.findAll().get(0);
        Hospital hospital = hospitalRepository.findAll().get(0);

        obito.setTipoObito(TipoObito.PCR);
        obito.setDataCadastro(Calendar.getInstance());
        obito.setDataObito(new DateTime(2014, 9, 1, 0, 0).toCalendar(Locale.getDefault()));
        obito.setCorpoEncaminhamento(CorpoEncaminhamento.NAO_ENCAMINHADO);
        obito.setAptoDoacao(true);
        obito.setSetor(setor.getId());
        obito.setHospital(hospital.getId());

        this.getCausasMortis(obito);
        obito.setPaciente(mapper.map(pacienteData.criarPaciente(df),
                PacienteDTO.class));
        obito.getPaciente().setNome("Jose");
        obito.getPaciente().setNumeroSUS("111111");
    }

    private void getCausasMortis(ObitoDTO obito) {
        CausaMortis causa1 = new CausaMortis();
        CausaMortis causa2 = new CausaMortis();
        CausaMortis causa3 = new CausaMortis();
        causa1.setNome("CausaObito1");
        causa2.setNome("CausaObito2");
        causa3.setNome("CausaObito3");

        obito.setPrimeiraCausaMortis(causa1);
        obito.setSegundaCausaMortis(causa2);
        obito.setTerceiraCausaMortis(causa3);
    }

    @Test
    public void salvarObito() {
        Long id = aplProcessoNotificacao.salvarNovaNotificacao(
                notificacao,
                notificacao.getNotificador()).getId();

        notificacao = aplProcessoNotificacao.obter(id);

        Assert.assertNotNull(notificacao.getObito());
        Assert.assertNotNull(notificacao.getObito().getId());

        Assert.assertNotNull(notificacao.getHistorico());
    }

    @Test
    public void salvarObitoDoacaoNaoAutorizada() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(false, null);
//        11 = Sorologia positiva Hepatite B, Contra indicação médica
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(11L));

        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        assertThat(processoNotificacao.getObito().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getDataAbertura(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao().getId(), equalTo(11L));
    }

    @Test
    public void salvarObitoDoacaoAutorizada() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(true, null);
//        11 = Sorologia positiva Hepatite B, Contra indicação médica
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(11L));

        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        assertThat(processoNotificacao.getObito().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getDataAbertura(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao(), nullValue());
    }

    @Test
    public void editarObitoDeInaptoParaApto() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(false, null);
//        11 = Sorologia positiva Hepatite B, Contra indicação médica
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(11L));

//        Quando salvar e não for autorizada.
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
        assertThat(processoNotificacao.getObito().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getDataAbertura(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao().getId(), equalTo(11L));

        Calendar dataCadastro = processoNotificacao.getObito().getDataCadastro();
        Calendar dataAbertura = processoNotificacao.getDataAbertura();

        processoNotificacao.getObito().setAptoDoacao(true);
//        Quando salvar e for autorizada.
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
        assertThat(processoNotificacao.getObito().getDataCadastro(), equalTo(dataCadastro));
        assertThat(processoNotificacao.getDataAbertura(), equalTo(dataAbertura));
        assertThat(processoNotificacao.getCausaNaoDoacao(), nullValue());
    }

    @Test
    public void analisarObito() {
        Long id = aplProcessoNotificacao.salvarNovaNotificacao(
                notificacao,
                notificacao.getNotificador()).getId();
        int quantidadeEstado = 0;

        notificacao = aplProcessoNotificacao.obter(id);
        id = aplProcessoNotificacao.entrarAnaliseObito(notificacao, notificacao.getNotificador());

        notificacao = aplProcessoNotificacao.obter(id);

        for (AtualizacaoEstadoDTO estado : notificacao.getHistorico()) {
            if (estado.getEstadoNotificacao() == EstadoNotificacaoEnum.EMANALISEOBITO) {
                quantidadeEstado++;
            }
        }

        Assert.assertTrue(quantidadeEstado > 0);
    }

    @Test
    public void recuperarNotificacoesNaoArquivadas() {

        aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador());

        List<ProcessoNotificacaoDTO> notificacoes = aplProcessoNotificacao
                .retornarNotificacaoNaoArquivada();

        Assert.assertTrue(notificacoes.size() > 0);
    }

    @Test
    public void salvarEntrevistaTest() {
        adicionarEntrevista();

        Assert.assertNotNull(notificacao.getEntrevista());
        Assert.assertNotNull(notificacao.getEntrevista().getId());

        Assert.assertNotNull(notificacao.getHistorico());
    }

    @Test
    public void salvarEntrevistaEntrevistaNaoRealizada() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(true, null);
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
        Calendar dataAbertura = processoNotificacao.getDataAbertura();

        processoNotificacao.setEntrevista(entrevistaData.criaEntrevista(df));
        processoNotificacao.getEntrevista().setEntrevistaRealizada(false);
//TODO: Ao setar a data de cadastro ocasionava o erro no teste dizendo que esta deveria estar no passado.
//        processoNotificacao.getEntrevista().setDataCadastro(null);
//        19 = Equipe de retirada não disponível, Problema estrutural
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(19L));

        processoNotificacao = aplProcessoNotificacao.salvarEntrevista(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        assertThat(processoNotificacao.getEntrevista().isEntrevistaRealizada(), is(false));
        assertThat(processoNotificacao.getEntrevista().isDoacaoAutorizada(), is(false));
        assertThat(processoNotificacao.getDataAbertura(), equalTo(dataAbertura));
        assertThat(processoNotificacao.getEntrevista().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao().getId(), is(19L));
        assertThat(processoNotificacao.getEntrevista().getResponsavel(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getResponsavel2(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getResponsavel2(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha1(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha2(), nullValue());
    }

    @Test
    public void salvarEntrevistaDoacaoNaoAutorizada() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(true, null);
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
        Calendar dataAbertura = processoNotificacao.getDataAbertura();

        processoNotificacao.setEntrevista(entrevistaData.criaEntrevista(df));
        processoNotificacao.getEntrevista().setEntrevistaRealizada(true);
        processoNotificacao.getEntrevista().setDoacaoAutorizada(false);
//        processoNotificacao.getEntrevista().setDataCadastro(null);
//        2 = Doador contrário à doação em vida, Recusa Familiar
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(2L));

        processoNotificacao = aplProcessoNotificacao.salvarEntrevista(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        assertThat(processoNotificacao.getEntrevista().isEntrevistaRealizada(), is(true));
        assertThat(processoNotificacao.getEntrevista().isDoacaoAutorizada(), is(false));
        assertThat(processoNotificacao.getDataAbertura(), equalTo(dataAbertura));
        assertThat(processoNotificacao.getEntrevista().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao().getId(), is(2L));
        assertThat(processoNotificacao.getEntrevista().getResponsavel(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getResponsavel2(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha1(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha2(), nullValue());
    }

    @Test
    public void salvarEntrevistaDoacaoAutorizada() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(true, null);
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
        Calendar dataAbertura = processoNotificacao.getDataAbertura();

        processoNotificacao.setEntrevista(entrevistaData.criaEntrevista(df));
        processoNotificacao.getEntrevista().setEntrevistaRealizada(true);
        processoNotificacao.getEntrevista().setDoacaoAutorizada(true);
//        2 = Doador contrário à doação em vida, Recusa Familiar
        processoNotificacao.setCausaNaoDoacao(causaNaoDoacaoRepository.findOne(2L));

        processoNotificacao = aplProcessoNotificacao.salvarEntrevista(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        assertThat(processoNotificacao.getEntrevista().isEntrevistaRealizada(), is(true));
        assertThat(processoNotificacao.getEntrevista().isDoacaoAutorizada(), is(true));
        assertThat(processoNotificacao.getDataAbertura(), equalTo(dataAbertura));
        assertThat(processoNotificacao.getEntrevista().getDataCadastro(), notNullValue());
        assertThat(processoNotificacao.getCausaNaoDoacao(), nullValue());
        assertThat(processoNotificacao.getEntrevista().getResponsavel(), notNullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha1(), notNullValue());
        assertThat(processoNotificacao.getEntrevista().getTestemunha2(), notNullValue());
    }

    @Test(expected = ViolacaoDeRIException.class)
    public void salvarEntrevistaComDadosInvalidos() {
        ProcessoNotificacao processoNotificacao = getProcessoComObito(true, null);
        processoNotificacao = aplProcessoNotificacao.salvarNovaNotificacao(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);

        processoNotificacao.setEntrevista(entrevistaData.criaEntrevista(df));
        processoNotificacao.getEntrevista().setEntrevistaRealizada(true);
        processoNotificacao.getEntrevista().setDoacaoAutorizada(true);
        processoNotificacao.getEntrevista().getResponsavel().setReligiao("AB");

        aplProcessoNotificacao.salvarEntrevista(mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class), 1L);
    }

//    @Test
//    public void salvarCaptacacaoTest() {
//        adicionarEntrevista();
//        adicionarCaptacao();
//
//        Assert.assertNotNull(notificacao.getCaptacao());
//        Assert.assertNotNull(notificacao.getCaptacao().getId());
//
//        Assert.assertNotNull(notificacao.getHistorico());
//    }

    private void adicionarCaptacao() {
        Captador captador = captadorData.criaCaptador(df);
        CaptacaoDTO captacaoDTO = mapper.map(captacaoData.criarCaptacao(df, captador), CaptacaoDTO.class);

        notificacao.setCaptacao(captacaoDTO);
        Long id = aplProcessoNotificacao.salvarCaptacao(notificacao.getId(), notificacao.getCaptacao(), captacaoDTO.getCaptador()).getId();

        notificacao = aplProcessoNotificacao.obter(id);
    }

    private void adicionarEntrevista() {
        EntrevistaDTO entrevista = mapper.map(
                entrevistaData.criaEntrevista(df), EntrevistaDTO.class);

        entrevista.setDataEntrevista(new DateTime(2014, 9, 1, 0, 0).toCalendar(Locale.getDefault()));
        entrevista.setDoacaoAutorizada(true);
        entrevista.setEntrevistaRealizada(true);

        Long id = aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador()).getId();
        notificacao = aplProcessoNotificacao.obter(id);

        notificacao.setEntrevista(entrevista);

        id = aplProcessoNotificacao.salvarEntrevista(notificacao, notificacao.getNotificador()).getId();
        notificacao = aplProcessoNotificacao.obter(id);
    }

    @Test
    //TODO: Utilizar um paciente cadastrado no teste
    public void obterProcessoPorPacienteNome() {
        salvarObito();
        analisarObito();
        salvarEntrevistaTest();
        List<ProcessoNotificacao> pn = aplProcessoNotificacao.obterPorPacienteNomeComEntrevistaDoacaoAutorizada("J");
        Assert.assertTrue(pn.size() > 0);
    }

    private ProcessoNotificacao getProcessoComObito(boolean aptoDoacao, Calendar dataCadastro) {
        ProcessoNotificacao processoNotificacao = new ProcessoNotificacao();
        processoNotificacao.setObito(obitoData.criaObito(df));
        processoNotificacao.getObito().setAptoDoacao(aptoDoacao);
        processoNotificacao.getObito().setDataCadastro(dataCadastro);
        return processoNotificacao;
    }
}
