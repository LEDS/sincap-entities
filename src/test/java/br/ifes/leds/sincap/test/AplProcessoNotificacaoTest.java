package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.*;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

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
    private Mapper mapper;
    @Autowired
    private PacienteData pacienteData;
    @Autowired
    private EntrevistaData entrevistaData;
    @Autowired
    private CaptacaoData captacaoData;
    @Autowired
    private CaptadorData captadorData;
    @Autowired
    private ResponsavelData responsavelData;
    @Autowired
    private TestemunhaData testemunhaData;
    @Autowired
    private DataFactory df;
    @Autowired
    private Factory factory;
    @Autowired
    private Utility utility;

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
        ObitoDTO obito = notificacao.getObito();
        Setor setor = setorRepository.findAll().get(0);
        Hospital hospital = hospitalRepository.findAll().get(0);

        obito.setTipoObito(TipoObito.PCR);
        obito.setDataCadastro(Calendar.getInstance());
        obito.setDataObito(Calendar.getInstance());
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
    public void salvarObito() throws ViolacaoDeRIException {
        Long id = aplProcessoNotificacao.salvarNovaNotificacao(
                notificacao,
                notificacao.getNotificador());

        notificacao = aplProcessoNotificacao.obter(id);

        Assert.assertNotNull(notificacao.getObito());
        Assert.assertNotNull(notificacao.getObito().getId());

        Assert.assertNotNull(notificacao.getHistorico());
    }

    @Test
    public void analisarObito() throws ViolacaoDeRIException {
        Long id = aplProcessoNotificacao.salvarNovaNotificacao(
                notificacao,
                notificacao.getNotificador());
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
    public void recuperarNotificacoesNaoArquivadas()
            throws ViolacaoDeRIException {

        aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador());

        List<ProcessoNotificacaoDTO> notificacoes = aplProcessoNotificacao
                .retornarNotificacaoNaoArquivada();

        Assert.assertTrue(notificacoes.size() > 0);
    }

    @Test
    public void salvarEntrevistaTest() throws ViolacaoDeRIException {
        adicionarEntrevista();

        Assert.assertNotNull(notificacao.getEntrevista());
        Assert.assertNotNull(notificacao.getEntrevista().getId());

        Assert.assertNotNull(notificacao.getHistorico());
    }

    @Test
    public void salvarCaptacacaoTest() throws ViolacaoDeRIException {
        adicionarEntrevista();
        adicionarCaptacao();

        Assert.assertNotNull(notificacao.getCaptacao());
        Assert.assertNotNull(notificacao.getCaptacao().getId());

        Assert.assertNotNull(notificacao.getHistorico());
    }

    private void adicionarCaptacao() {
        Captador captador = captadorData.criaCaptador(df);
        CaptacaoDTO captacaoDTO = mapper.map(captacaoData.criarCaptacao(df, captador), CaptacaoDTO.class);

        notificacao.setCaptacao(captacaoDTO);
        Long id = aplProcessoNotificacao.salvarCaptacao(notificacao.getId(), notificacao.getCaptacao(), captacaoDTO.getCaptador());

        notificacao = aplProcessoNotificacao.obter(id);
    }

    private void adicionarEntrevista() throws ViolacaoDeRIException {
        responsavelData.criaResponsavelRandom(df, 30);
        testemunhaData.criaTestemunhaRandom(df, 30);
        EntrevistaDTO entrevista = mapper.map(
                entrevistaData.criaEntrevista(df), EntrevistaDTO.class);
        Long id = aplProcessoNotificacao.salvarNovaNotificacao(notificacao, notificacao.getNotificador());
        notificacao = aplProcessoNotificacao.obter(id);

        notificacao.setEntrevista(entrevista);

        id = aplProcessoNotificacao.salvarEntrevista(notificacao, notificacao.getNotificador());
        notificacao = aplProcessoNotificacao.obter(id);
    }

    @Test
    //TODO: Utilizar um paciente cadastrado no teste
    public void obterProcessoPorPacienteNome() throws ViolacaoDeRIException{
        salvarObito();
        //analisarObito();
        //salvarEntrevistaTest();
        List<ProcessoNotificacao> pn = aplProcessoNotificacao.obterPorPacienteNome("Jo");
        Assert.assertTrue(pn.size()>0);
    }
}
