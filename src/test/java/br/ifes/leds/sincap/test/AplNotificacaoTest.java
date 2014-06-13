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
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplNotificacao;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import org.junit.Assert;
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
    private NotificadorRepository notificadorRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private SetorRepository setorRepository;

    private ProcessoNotificacao notificacao;

    @Before
    public void before() {
        notificacao = new ProcessoNotificacao();
        
        this.notificacao.setDataAbertura(Calendar.getInstance());
        this.notificacao.setArquivado(false);
        
        this.getEstadoNotificacao(this.notificacao);
        this.getObito(this.notificacao);
        
        Notificador notificador = notificadorRepository.findAll().get(0);        
        this.notificacao.setNotificador(notificador);
    }
    
    @Test
    public void salvar(){
        aplNotificacao.salvar(this.notificacao);
        System.out.println(this.notificacao.getObito().getId());
        Assert.assertNotSame(0, this.notificacao.getObito().getId());
    }
    
    private void getEstadoNotificacao(ProcessoNotificacao notificacao) {
        Notificador notificador = notificadorRepository.findAll().get(0);
        
        if (notificacao.getHistorico() == null){
           List<AtualizacaoEstado> listEstados = new ArrayList<>();
           notificacao.setHistorico(listEstados);
        }
        
        AtualizacaoEstado novoEstado = new AtualizacaoEstado();
        novoEstado.setFuncionario(notificador);
        novoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISE);
        
        notificacao.getHistorico().add(novoEstado);
    }
    
    private void getObito(ProcessoNotificacao notificacao) {
        Obito obito = new Obito();
        Setor setor = setorRepository.findAll().get(0);
        
        obito.setTipoObito(TipoObito.PCR);
        obito.setDataEvento(Calendar.getInstance());
        obito.setDataObito(Calendar.getInstance());
        obito.setCorpoEncaminhamento(CorpoEncaminhamento.NAO_ENCAMINHADO);
        obito.setAptoDoacao(true);        
        obito.setSetor(setor);
        
        this.getCausasMortis(obito);
        this.getPaciente(obito);
        
        notificacao.setObito(obito);
    }
    
    private void getCausasMortis(Obito obito){
        CausaMortis causa1 = new CausaMortis();
        CausaMortis causa2 = new CausaMortis();
        CausaMortis causa3 = new CausaMortis();
        CausaMortis causa4 = new CausaMortis();
        causa1.setNome("CausaObito1");
        causa1.setNome("CausaObito2");
        causa1.setNome("CausaObito3");
        causa1.setNome("CausaObito4");
        
        obito.setPrimeiraCausaMortis(causa1);
        obito.setSegundaCausaMortis(causa2);
        obito.setTerceiraCausaMortis(causa3);
        obito.setQuartaCausaMortis(causa4);
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
//    public void recuperarNotificacoesNaoArquivadas() {
//   
        
//        List<Notificacao> notificacoes = aplNotificacao.retornarNotificacaoNaoArquivada(0, 10, "dataAbertura");
//
//        Assert.assertNotNull(notificacoes);
//    }
    
//    @Test
//    public void retornaNotificacoesPorInstituicao(){
//        
//        salvar();
//        Instituicao instituicao = notificacao.getInstituicao();
//        Assert.assertNotNull(instituicao);
//        Assert.assertNotSame(0, instituicao.getId());
//        List<Notificacao> notificacoes = aplNotificacao.retornarNotificacao(instituicao.getId());
//        Assert.assertNotNull(notificacoes);
//        Assert.assertNotSame(0, notificacoes.size());
//    }
}
