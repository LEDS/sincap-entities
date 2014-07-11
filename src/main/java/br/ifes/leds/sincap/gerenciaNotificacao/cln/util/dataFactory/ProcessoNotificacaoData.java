/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import java.util.Calendar;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class ProcessoNotificacaoData {
    
    @Autowired
    private ProcessoNotificacaoRepository processoNotificacaoRepository;

    @Autowired
    private NotificadorRepository notificadorRepository;
    
    @Autowired
    private CaptadorRepository captadorRepository;
    
    @Autowired
    private AtualizacaoEstadoRepository atualizacaoEstadoRepository;
    
    @Autowired
    private Factory fabrica;
    
    @Autowired
    private ObitoData obitoData;
    
    @Autowired
    private EntrevistaData entrevistaData;
    
    @Autowired
    private CaptacaoData captacaoData;
    
        
    private AtualizacaoEstado atualizacaoEstado;
    
    private EstadoNotificacaoEnum estadoNotificacao;
    
    private ProcessoNotificacao processoNotificacao;
    private List<Notificador> listNotificador;
    private Notificador notificador;
    private List<AtualizacaoEstado> listAtualizacaoEstado;
    
    private Obito obito;
    private Entrevista entrevista;
    private Captacao captacao;
    private List<Captador> listCaptador;
    private CausaNaoDoacao causaNaoDoacao;
    
    private Calendar dataAbertura;
    private Calendar dataArquivamento;
    
    public void criarAnaliseObitoRandom(DataFactory df,Integer qtdAna){
        for (int i = 0; i < qtdAna;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            salvarProcesso(pn);
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,1));
        }
    }
    public AtualizacaoEstado AtualizaEstadoNotificacao(ProcessoNotificacao pn,Integer etapa){
        atualizacaoEstado = fabrica.criaObjeto(AtualizacaoEstado.class);
        
        atualizacaoEstado.setFuncionario(pn.getNotificador());
        
        if (etapa == 1) {
            atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISEOBITO);
        } 
        else {
            if(etapa == 2){
                    atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISEENTREVISTA);
               }
                else {
                    if(etapa == 3){
                        atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISECAPTACAO);
                    }
                }    
            }
            return atualizacaoEstado;
        }    
    
    
    public void salvaEstadoNotificacao(AtualizacaoEstado ae){
        atualizacaoEstadoRepository.save(ae);
    }
    
    public ProcessoNotificacao criarAnaliseObito(DataFactory df){
        
        processoNotificacao = fabrica.criaObjeto(ProcessoNotificacao.class);
        dataAbertura = Calendar.getInstance();
        obito = obitoData.criaObito(df);
        
        processoNotificacao.setArquivado(false);
        listNotificador = notificadorRepository.findAll();
        processoNotificacao.setNotificador(df.getItem(listNotificador));
        processoNotificacao.setObito(obito);
        dataAbertura.setTime(df.getDateBetween(df.getDate(2000, 01, 01), df.getDate(2014, 12, 31)));
        processoNotificacao.setDataAbertura(dataAbertura);
        processoNotificacao.setCodigo(df.getNumberText(8));
        processoNotificacao.setEntrevista(null);
        
        return processoNotificacao;
    }
    
    public void salvarProcesso(ProcessoNotificacao pn){
        if(processoNotificacao.getCaptacao() != null){
            captacaoData.salvarCaptacao(processoNotificacao.getCaptacao());
        }
        if (processoNotificacao.getEntrevista() != null){
            entrevistaData.salvaEntrevista(processoNotificacao.getEntrevista());
        }    
        obitoData.salvaObito(processoNotificacao.getObito());
        processoNotificacaoRepository.save(pn);
    }
    
    public Entrevista criarEntrevista(DataFactory df){
        entrevista = entrevistaData.criaEntrevista(df);
        return entrevista;
    }
    
    
    public void criaEntrevistaRadom(DataFactory df,Integer QtdEnt){
     for (int i = 0; i < QtdEnt;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            pn.setEntrevista(criarEntrevista(df));
            salvarProcesso(pn);
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,1));
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,2));
        }
    }
    
    public Captacao criaCaptacao(DataFactory df){
        listCaptador = captadorRepository.findAll();
        captacao = captacaoData.criarCaptacao(df, df.getItem(listCaptador));
        return captacao;
    } 
    
    public void criaCaptacaoRadom(DataFactory df,Integer QtdCap){
     for (int i = 0; i < QtdCap;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            pn.setEntrevista(criarEntrevista(df));
            pn.setCaptacao(criaCaptacao(df));
            salvarProcesso(pn);
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,1));
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,2));
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,3));
        }
    }
}
