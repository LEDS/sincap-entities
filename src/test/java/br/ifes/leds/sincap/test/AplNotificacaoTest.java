/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplMotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplMotivoRecusa;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplNotificacao;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 20102BSI0553
 */
public class AplNotificacaoTest extends AbstractionTest {

    @Autowired
    private AplNotificacao aplNotificacao;

    @Autowired
    private AplMotivoInviabilidade aplMotivoInviabilidade;

    @Autowired
    private NotificadorRepository notificadorRepository;

    @Autowired
    private ObitoRepository obitoRepository;

    @Autowired
    private AplMotivoRecusa aplMotivoRecusa;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    private ProcessoNotificacao notificacao;

    @Before
    public void before() {
        this.notificacao.setDataAbertura(Calendar.getInstance());
        this.notificacao.setArquivado(false);
        this.notificacao.setNotificador(null);
        
        this.getEstadoNotificacao(this.notificacao);
        this.getObito(this.notificacao);
        Notificador notificador = notificadorRepository.findAll().get(0);
        this.notificacao.setNotificador(notificador);
    }
    
    @Test
    public void salvar(){
        aplNotificacao.salvar(notificacao);
    }
    
    private void getEstadoNotificacao(ProcessoNotificacao notificacao) {
        Notificador notificador = notificadorRepository.findAll().get(0);
        
        AtualizacaoEstado novoEstado = new AtualizacaoEstado();
        novoEstado.setFuncionario(notificador);
        novoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISE);
        
        notificacao.getHistorico().add(novoEstado);
    }
    
    private void getObito(ProcessoNotificacao notificacao) {
        Obito obito = new Obito();
        obito.setTipoObito(TipoObito.PCR);
        obito.setDataEvento(Calendar.getInstance());
        obito.setDataObito(Calendar.getInstance());
        obito.setCorpoEncaminhamento(CorpoEncaminhamento.NAO_ENCAMINHADO);
        
        this.getPaciente(obito);
    }
    
    private void getPaciente(Obito obito) {
        Paciente paciente = new Paciente();
        Telefone tel = new Telefone();
        tel.setNumero("27 91111 1111");
        
        paciente.setDataInternacao(Calendar.getInstance());
        paciente.setDataNascimento(Calendar.getInstance());
        paciente.setDocumentoSocial("2123123123");
        paciente.setEstadoCivil(EstadoCivil.DIVORCIADO);
        paciente.setNacionalidade("Brasileiro");
        paciente.setNomeMae("Nome da Mãe");
        paciente.setNumeroProntuario("123654987");
        paciente.setNumeroSUS("852456963147");
        paciente.setProfissao("Profissao");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setNome("Nome do Paciente");
        paciente.setTelefone(tel);
        
        this.getEndereco(paciente);
        
        obito.setPaciente(paciente);
    }
    
    private void getEndereco(Paciente paciente) {
        Endereco endereco = new Endereco();
        Estado estado = estadoRepository.findAll().get(0);
        Cidade cidade = cidadeRepository.findByIdEstado(estado.getId()).get(0);
        Bairro bairro = bairroRepository.findByIdCidade(cidade.getId()).get(0);
        
        
        endereco.setCep("123654-321");
        endereco.setLogradouro("Rua X");
        endereco.setNumero("25");
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        
        paciente.setEndereco(endereco);
    }

//    @Test
//    public void salvar() {
//        aplNotificacao.salvar(notificacao);
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getTestemunhas());
//        for (Testemunha test : notificacao.getObito().getPaciente().getDoacao().getTestemunhas()) {
//            Assert.assertNotSame(0, test);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getResponsaveis());
//        for (Responsavel resp : notificacao.getObito().getPaciente().getDoacao().getResponsaveis()) {
//            Assert.assertNotSame(0, resp);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getMotivoRecusa());
//        for (MotivoRecusa motivo : notificacao.getObito().getPaciente().getDoacao().getMotivoRecusa()) {
//            Assert.assertNotSame(0, motivo);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getDoacao().getId());
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getEndereco());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getEndereco().getId());
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getId());
//        
//        // Responsavel Obito
//        Responsavel resp = notificacao.getObito().getResponsavel();
//        Assert.assertNotSame(0, resp.getId());
//
//        //Primeira causa mortis
//        Assert.assertNotNull(notificacao.getObito().getPrimeiraCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getPrimeiraCausaMortis().getId());
//
//        //Segunda causa mortis
//        Assert.assertNotNull(notificacao.getObito().getSegundaCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getSegundaCausaMortis().getId());
//
//        //Terceira causa mortis
//        Assert.assertNotNull(notificacao.getObito().getTerceiraCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getTerceiraCausaMortis().getId());
//
//        //Quarta causa mortis
//        Assert.assertNotNull(notificacao.getObito().getQuartaCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getQuartaCausaMortis().getId());
//
//        Assert.assertNotSame(0, notificacao.getId());
//        Assert.assertNotNull(notificacao.getCodigo());
//        Assert.assertNotNull(notificacao.getDataAbertura());
//
//        //Doacao
//        Doacao doacao = notificacao.getObito().getPaciente().getDoacao();
//        Assert.assertNotSame(0, doacao.getId());
//
//        //Captacao
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getCaptacao());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getDoacao().getCaptacao().getId());
//    }
//    
//    @Test
//    public void salvarPorEtapa(){
//        aplNotificacao.salvarEtapaObito(notificacao);
//        aplNotificacao.salvarEtapaDoacao(notificacao);
//        aplNotificacao.salvarEtapaCaptacao(notificacao);
//        
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getTestemunhas());
//        for (Testemunha test : notificacao.getObito().getPaciente().getDoacao().getTestemunhas()) {
//            Assert.assertNotSame(0, test);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getResponsaveis());
//        for (Responsavel resp : notificacao.getObito().getPaciente().getDoacao().getResponsaveis()) {
//            Assert.assertNotSame(0, resp);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getMotivoRecusa());
//        for (MotivoRecusa motivo : notificacao.getObito().getPaciente().getDoacao().getMotivoRecusa()) {
//            Assert.assertNotSame(0, motivo);
//        }
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getDoacao().getId());
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getEndereco());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getEndereco().getId());
//
//        Assert.assertNotNull(notificacao.getObito().getPaciente());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getId());
//
//        //Primeira causa mortis
//        Assert.assertNotNull(notificacao.getObito().getPrimeiraCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getPrimeiraCausaMortis().getId());
//
//        //Segunda causa mortis
//        Assert.assertNotNull(notificacao.getObito().getSegundaCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getSegundaCausaMortis().getId());
//
//        //Terceira causa mortis
//        Assert.assertNotNull(notificacao.getObito().getTerceiraCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getTerceiraCausaMortis().getId());
//
//        //Quarta causa mortis
//        Assert.assertNotNull(notificacao.getObito().getQuartaCausaMortis());
//        Assert.assertNotSame(0, notificacao.getObito().getQuartaCausaMortis().getId());
//
//        Assert.assertNotSame(0, notificacao.getId());
//        Assert.assertNotNull(notificacao.getCodigo());
//        Assert.assertNotNull(notificacao.getDataAbertura());
//
//        //Doacao
//        Doacao doacao = notificacao.getObito().getPaciente().getDoacao();
//        Assert.assertNotSame(0, doacao.getId());
//        
//        // Responsavel Obito
//        Responsavel resp = notificacao.getObito().getResponsavel();
//        Assert.assertNotSame(0, resp.getId());
//
//        //Captacao
//        Assert.assertNotNull(notificacao.getObito().getPaciente().getDoacao().getCaptacao());
//        Assert.assertNotSame(0, notificacao.getObito().getPaciente().getDoacao().getCaptacao().getId());
//    }
//    
//    
//
//    private Obito getObito() {
//        Obito obito = new Obito();
//        obito.setDataObito(Calendar.getInstance());
//
//        CausaMortis causaMortis1 = new CausaMortis();
//        causaMortis1.setDescricao("teste1");
//
//        CausaMortis causaMortis2 = new CausaMortis();
//        causaMortis2.setDescricao("teste2");
//
//        CausaMortis causaMortis3 = new CausaMortis();
//        causaMortis3.setDescricao("teste3");
//
//        CausaMortis causaMortis4 = new CausaMortis();
//        causaMortis4.setDescricao("teste4");
//
//        Paciente paciente = this.getPaciente();
//        Responsavel resp = this.getResponsavel();
//        obito.setPaciente(paciente);
//        obito.setResponsavel(resp);
//        obito.setPrimeiraCausaMortis(causaMortis1);
//        obito.setSegundaCausaMortis(causaMortis2);
//        obito.setTerceiraCausaMortis(causaMortis3);
//        obito.setQuartaCausaMortis(causaMortis4);
//
//        return obito;
//    }
//
//    private Paciente getPaciente() {
//        Paciente paciente = new Paciente();
//        Doacao doacao = getDoacao();
//
//        /*que sacanagem, cara! kkkkkkkkkkkkkkkkkkk*/
//        paciente.setNome("Lucas Possatti");
//
//        paciente.setEndereco(this.getEndereco());
//        paciente.setDoacao(doacao);
//
//        return paciente;
//    }
//
//    private Responsavel getResponsavel() {
//        Responsavel resp = new Responsavel();
//        Telefone tel = new Telefone();
//        List<Telefone> tels = new ArrayList<Telefone>();
//        
//        tel.setDdd("27");
//        tel.setTipo(TipoTelefone.CELULAR);
//        tel.setNumero("8888-8888");
//        
//        tels.add(tel);
//        
//        resp.setNome("Nome Responsavel");
//        resp.setRg("1111111111");
//        resp.setTelefones(tels);
//        
//        return resp;
//    }
//
//    private Doacao getDoacao() {
//        Doacao doacao = new Doacao();
//        Captacao captacao = getCaptacao();
//
//        List<MotivoRecusa> contraIndicacao = this.getContraIndicacoes();
//
//        List<MotivoRecusa> recusaFamiliar = this.getRecusaFamiliar();
//
//        for (MotivoRecusa motivoRecusa : recusaFamiliar) {
//            doacao.addMotivoRecusa(motivoRecusa);
//        }
//
//        for (MotivoRecusa motivoRecusa : contraIndicacao) {
//            doacao.addMotivoRecusa(motivoRecusa);
//        }
//
//        doacao.setAutorizada(true);
//
//        Set<Responsavel> responsaveis = this.getResponsavelDoacao();
//        Set<Testemunha> testemunhas = this.getTestemunhas();
//        
//        doacao.setCaptacao(captacao);
//        doacao.setResponsaveis(responsaveis);
//        doacao.setTestemunhas(testemunhas);
//
//        return doacao;
//    }
//
//    private List<MotivoRecusa> getContraIndicacoes() {
//        TipoMotivoRecusa tipoMotivoRecusa = new TipoMotivoRecusa();
//        tipoMotivoRecusa.setNome("Motivo 1");
//        aplMotivoRecusa.salvar(tipoMotivoRecusa);
//
//        MotivoRecusa motivoRecusa = new MotivoRecusa();
//        motivoRecusa.setNome("Contra Indicacao Medica 1");
//        motivoRecusa.setTipoMotivoRecusa(tipoMotivoRecusa);
//
//        aplMotivoRecusa.salvar(motivoRecusa);
//
//        return aplMotivoRecusa.obterTodosContraindicacaoMedica();
//    }
//
//    private List<MotivoRecusa> getRecusaFamiliar() {
//        TipoMotivoRecusa tipoMotivoRecusa = new TipoMotivoRecusa();
//        tipoMotivoRecusa.setNome("Motivo 1");
//        aplMotivoRecusa.salvar(tipoMotivoRecusa);
//
//        MotivoRecusa motivoRecusa = new MotivoRecusa();
//        motivoRecusa.setNome("Recusa Familiar");
//        motivoRecusa.setTipoMotivoRecusa(tipoMotivoRecusa);
//
//        aplMotivoRecusa.salvar(motivoRecusa);
//
//        return aplMotivoRecusa.obterTodosRecusaFamiliar();
//    }
//
//    private Set<Responsavel> getResponsavelDoacao() {
//        Responsavel resp = new Responsavel();
//        Set<Responsavel> responsaveis = new HashSet<Responsavel>();
//
//        resp.setNome("Resp Doacao");
//        resp.setRg("258456321");
//        resp.setParentesco(Parentesco.IRMAOS);
//        resp.setEstadoCivil(EstadoCivil.DIVORCIADO);
//
//        resp.setTelefones(this.getTelefones());
//        resp.setEndereco(this.getEndereco());
//        resp.setProfissao("Profissao");
//        resp.setNacionalidade("Brasil");
//
//        responsaveis.add(resp);
//        return responsaveis;
//    }
//
//    private List<Telefone> getTelefones() {
//        List<Telefone> telefones = new ArrayList<Telefone>();
//        Telefone tel1 = new Telefone();
//        Telefone tel2 = new Telefone();
//
//        tel1.setNumero("1111-1111");
//        tel1.setTipo(TipoTelefone.RESIDENCIAL);
//        tel2.setNumero("2222-222");
//        tel2.setTipo(TipoTelefone.RESIDENCIAL);
//
//        telefones.add(tel1);
//        telefones.add(tel2);
//
//        return telefones;
//    }
//
//    private Endereco getEndereco() {
//        Endereco ender = new Endereco();
//
//        ender.setCEP("25812-225");
//        ender.setComplemento("Complemento");
//        ender.setLogradouro("Rua");
//        ender.setNumero("000");
//        ender.setBairro(this.getBairro());
//        ender.setCidade(this.getCidade());
//        ender.setEstado(this.getEstado());
//
//        return ender;
//    }
//
//    private Bairro getBairro() {
//        Bairro bairro = new Bairro();
//        bairro.setNome("Bairro1");
//
//        bairroRepository.save(bairro);
//
//        return bairro;
//    }
//
//    private Cidade getCidade() {
//        Cidade cidade = new Cidade();
//        cidade.setNome("cidade1");
//
//        cidadeRepository.save(cidade);
//        return cidade;
//    }
//
//    private Estado getEstado() {
//        Estado estado = new Estado();
//        estado.setNome("Estado1");
//        estado.setSigla("ES");
//
//        estadoRepository.save(estado);
//
//        return estado;
//    }
//
//    private Set<Testemunha> getTestemunhas() {
//        Set<Testemunha> testemunhas = new HashSet<Testemunha>();
//        Testemunha test1 = new Testemunha();
//        Testemunha test2 = new Testemunha();
//        test1.setNome("Nome Testemunha 1");
//        test2.setNome("Nome Testemunha 2");
//
//        test1.setCpf("12345698741");
//        test2.setCpf("22345698741");
//
//        return testemunhas;
//    }
//
//    @Test
//    public void recuperarNotificacoesNaoArquivadas() {
//   
        
//        List<Notificacao> notificacoes = aplNotificacao.retornarNotificacaoNaoArquivada(0, 10, "dataAbertura");
//
//        Assert.assertNotNull(notificacoes);
//    }
//
//    private Captacao getCaptacao() {
//
//        Captacao captacao = new Captacao();
//        Set<MotivoInviabilidade> motivosInviabilidades = new HashSet<>();
//
//        motivosInviabilidades.add(aplMotivoInviabilidade.buscar("Problema Logístico 1"));
//        motivosInviabilidades.add(aplMotivoInviabilidade.buscar("Problema Estrutural 2"));
//
//        captacao.setComentario("Comentários da captação");
//        captacao.setCorneasCaptadas(true);
//        captacao.setEquipeCaptacaoDisponivel(true);
//        captacao.setMotivosInviabilidade(motivosInviabilidades);        
//        
//        return captacao;
//    }
//    
////    @Test
////    public void retornaNotificacoesPorInstituicao(){
////        
////        salvar();
////        Instituicao instituicao = notificacao.getInstituicao();
////        Assert.assertNotNull(instituicao);
////        Assert.assertNotSame(0, instituicao.getId());
////        List<Notificacao> notificacoes = aplNotificacao.retornarNotificacao(instituicao.getId());
////        Assert.assertNotNull(notificacoes);
////        Assert.assertNotSame(0, notificacoes.size());
////    }
}
