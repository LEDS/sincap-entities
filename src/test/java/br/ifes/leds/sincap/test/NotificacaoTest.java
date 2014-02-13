/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cgd.MotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplMotivoRecusa;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Doacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Notificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplNotificacao;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 20102BSI0553
 */
public class NotificacaoTest extends AbstractionTest{
    
    @Autowired
    private AplNotificacao aplNotificacao;
    
    @Autowired
    private NotificadorRepository notificadorRepository;
    
    @Autowired
    private ObitoRepository obitoRepository;
    
    @Autowired
    private AplMotivoRecusa aplMotivoRecusa;
    
    private Notificacao notificao;
    
    @Before
    public void before()
    {
        notificao = new Notificacao();
        
        Notificador notificador = new Notificador();
        notificador.setCpf("12345");
        notificadorRepository.save(notificador);        
        notificao.setNotificador(notificador);
       
        //TODO -- colocr data e hora em variavel
        Obito obito = this.getObito();

        notificao.setObito(obito); 
        
        
    }
    
    @Test
    public void salvar ()
    {
        aplNotificacao.salvar(notificao);
        
        Assert.assertNotSame(0, notificao.getId());
        //Primeira causa mortis
        Assert.assertNotNull(notificao.getObito().getPrimeiraCausaMortis());
        Assert.assertNotSame(0, notificao.getObito().getPrimeiraCausaMortis().getId());
        
        //Segunda causa mortis
        Assert.assertNotNull(notificao.getObito().getSegundaCausaMortis());
        Assert.assertNotSame(0, notificao.getObito().getSegundaCausaMortis().getId());

        //Terceira causa mortis
        Assert.assertNotNull(notificao.getObito().getTerceiraCausaMortis());
        Assert.assertNotSame(0, notificao.getObito().getTerceiraCausaMortis().getId());
        
        //Quarta causa mortis
        Assert.assertNotNull(notificao.getObito().getQuartaCausaMortis());
        Assert.assertNotSame(0, notificao.getObito().getQuartaCausaMortis().getId());

        Assert.assertNotNull(notificao.getCodigo());
        Assert.assertNotNull(notificao.getDataAbertura());
        
        //Doacao
        Doacao doacao = notificao.getObito().getPaciente().getDoacao();
        Assert.assertNotSame(0, doacao.getId());
    }
    
    private Obito getObito(){
        Obito obito = new Obito();
        obito.setDataObito(Calendar.getInstance());
        
        CausaMortis causaMortis1 = new CausaMortis();
        causaMortis1.setDescricao("teste1");
        
        CausaMortis causaMortis2 = new CausaMortis();
        causaMortis2.setDescricao("teste2");
        
        CausaMortis causaMortis3 = new CausaMortis();
        causaMortis3.setDescricao("teste3");
        
        CausaMortis causaMortis4 = new CausaMortis();
        causaMortis4.setDescricao("teste4");
        
        Paciente paciente = this.getPaciente();
        obito.setPaciente(paciente);
        obito.setPrimeiraCausaMortis(causaMortis1);
        obito.setSegundaCausaMortis(causaMortis2);
        obito.setTerceiraCausaMortis(causaMortis3);
        obito.setQuartaCausaMortis(causaMortis4);
        
        return obito;
    }
    
    private Paciente getPaciente(){
        Paciente paciente = new Paciente();
        paciente.setNome("Lucas Possatti");
        
        Responsavel responsavel = this.getResponsavel();
        Doacao doacao = getDoacao();
        paciente.setResponsavel(responsavel);
        paciente.setDoacao(doacao);
        
        return paciente;        
    }
    
    private Responsavel getResponsavel(){
        return new Responsavel();
    }
    
    private Doacao getDoacao(){
        Doacao doacao = new Doacao();
        List<MotivoRecusa> contraIndicacao = this.getContraIndicacoes();
        Set <MotivoRecusa> listCIM = new HashSet<MotivoRecusa>(contraIndicacao);
        doacao.setContraIndicacaoMedica(listCIM);
        
        return doacao;
    }
    
    private List<MotivoRecusa> getContraIndicacoes(){
        TipoMotivoRecusa tipoMotivoRecusa = new TipoMotivoRecusa();
        tipoMotivoRecusa.setNome("Motivo 1");
        aplMotivoRecusa.salvar(tipoMotivoRecusa);
        
        MotivoRecusa motivoRecusa = new MotivoRecusa();
        motivoRecusa.setNome("Contra Indicacao Medica 1");
        motivoRecusa.setTipoMotivoRecusa(tipoMotivoRecusa);
        
        aplMotivoRecusa.salvar(motivoRecusa);
        
        return aplMotivoRecusa.obterTodosContraindicacaoMedica();
        
    }
    @Test
    public void recuperarNotificacoesNaoArquivadas() {
        
        List<Notificacao> notificacoes = aplNotificacao.retornarNotificacaoNaoArquivada(0, 10, "dataAbertura");
        
        Assert.assertNotNull(notificacoes);
    }
}
