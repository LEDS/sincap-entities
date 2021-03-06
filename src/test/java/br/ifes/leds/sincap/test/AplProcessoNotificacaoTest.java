package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.dto.FuncionarioDTO;
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

    @Test
    public void salvarNotificacaoComComentarioObito(){

        ComentarioDTO comentario = criaComentario(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO.toString(),"Comentario Notificacao");


        ProcessoNotificacao processoSalvo = aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador(), comentario);

        Assert.assertEquals(1,processoSalvo.getComentarios().size());
        Assert.assertNotNull(processoSalvo.getComentarios());

    }


    @Test
    public void confirmarObitoComComentario(){

        ComentarioDTO comentarioNotificacao = criaComentario(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO.toString(),"Comentario Notificacao");

        Long id = aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador(),comentarioNotificacao).getId();
        notificacao = aplProcessoNotificacao.obter(id);

        ComentarioDTO comentarioAnalise = criaComentario(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO.toString(),"Comentario Confirmação");

        defineNoProcesso(comentarioAnalise,notificacao);

        aplProcessoNotificacao.validarAnaliseObito(notificacao, notificacao.getNotificador());
        notificacao = aplProcessoNotificacao.obter(id);

        Assert.assertEquals(2,notificacao.getComentarios().size());
        Assert.assertNotNull(notificacao.getComentarios());
    }

    @Test
    public void recusarObitoComComentario(){

        ComentarioDTO comentarioNotificacao = criaComentario(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO.toString(),"Comentario Notificacao");

        Long id = aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador(),comentarioNotificacao).getId();
        notificacao = aplProcessoNotificacao.obter(id);

        ComentarioDTO comentarioAnalise = criaComentario(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO.toString(),"Comentario Recusa");

        defineNoProcesso(comentarioAnalise,notificacao);

        aplProcessoNotificacao.recusarAnaliseObito(notificacao, notificacao.getNotificador());
        notificacao = aplProcessoNotificacao.obter(id);

        Assert.assertEquals(2,notificacao.getComentarios().size());
        Assert.assertNotNull(notificacao.getComentarios());
    }

    private ComentarioDTO criaComentario(String momento,String descricao) {
    /*Cria o DTO do funcionário a partir dos parâmetros passados*/
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(notificacao.getNotificador());


        /*Cria o DTO do comentário a partir dos parâmetros passados*/
        ComentarioDTO comentario = new ComentarioDTO();
        comentario.setFuncionario(funcionarioDTO);
        comentario.setDataComentario(Calendar.getInstance());
        comentario.setDescricao(descricao);
        comentario.setMomento(momento);

        return comentario;
    }

    public void defineNoProcesso(ComentarioDTO comentario, ProcessoNotificacaoDTO processo) {

        /*Faz o link entre o processo e o comentário*/
        processo.getComentarios().add(comentario);
    }
}
