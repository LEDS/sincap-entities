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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos ProcessoNotificacao randomicos.
 *
 * @author aleao
 * @version 1.0
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
    private Calendar dataObito;
    
    
    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Óbito de forma randomica.
     * @param df - instancia DataFactory.
     * @param qtdAna - quantidade de processos.
     */
    public void criarAnaliseObitoRandom(DataFactory df,Integer qtdAna){
        for (int i = 0; i < qtdAna;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
                salvaEstadoNotificacao(ae);   
            }    
            salvarProcesso(pn);
        }
    }
    
    /**Método responsável por alterar o estado da notificação de acordo com a sua etapa.
     * @param pn - objeto ProcessoNotificacao.
     * @param etapa - etapa do processo. 
     * @return atualizacaoEstado - Objeto AtualizacaoEstado.
     */
    public AtualizacaoEstado AtualizaEstadoNotificacao(ProcessoNotificacao pn,Integer etapa){
        atualizacaoEstado = fabrica.criaObjeto(AtualizacaoEstado.class);
        
        atualizacaoEstado.setFuncionario(pn.getNotificador());
        
        if (etapa == 1) {
            atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
            atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISEOBITO);
        } 
        else {
            if(etapa == 2){
                    atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
                    atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISEENTREVISTA);
               }
                else {
                    if(etapa == 3){
                        atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
                        atualizacaoEstado.setEstadoNotificacao(estadoNotificacao.AGUARDANDOANALISECAPTACAO);
                    }
                }    
            }
            return atualizacaoEstado;
        }    
    
    
    /**Método responsável por salvar no banco  de dados o estado da notificação.
     * @param ae - objeto AtualizacaoEstado.
     */
    public void salvaEstadoNotificacao(AtualizacaoEstado ae){
        atualizacaoEstadoRepository.save(ae);
    }
    
    /**Método responsável por criar um processo de notificação randomico até a
     * etapa de Analise de Óbito.
     * @param df - instancia DataFactory.
     * @return processoNotificacao - objeto ProcessoNotificacao.
     */
    public ProcessoNotificacao criarAnaliseObito(DataFactory df){
        
        processoNotificacao = fabrica.criaObjeto(ProcessoNotificacao.class);
        dataAbertura = Calendar.getInstance();
        obito = obitoData.criaObito(df);
        Date dataIni = removeDias(obito.getDataObito().getTime(), 2);

        
        processoNotificacao.setArquivado(false);
        listNotificador = notificadorRepository.findAll();
        processoNotificacao.setNotificador(df.getItem(listNotificador));
        processoNotificacao.setObito(obito);
        dataAbertura.setTime(df.getDateBetween(dataIni, obito.getDataObito().getTime()));
        processoNotificacao.setDataAbertura(dataAbertura);
        processoNotificacao.setCodigo(df.getNumberText(8));
        processoNotificacao.setEntrevista(null);
        
        return processoNotificacao;
    }
    
    /**Método responsável por salvar no banco de dados um Objeto ProcessoNotificacao.
     * @param pn - Objeto ProcessoNotificacao.
     */
    public void salvarProcesso(ProcessoNotificacao pn){
        if(pn.getCaptacao() != null){
            captacaoData.salvarCaptacao(pn.getCaptacao());
        }
        if (pn.getEntrevista() != null){
            entrevistaData.salvaEntrevista(pn.getEntrevista());
        }    
        obitoData.salvaObito(pn.getObito());
        processoNotificacaoRepository.save(pn);
    }
    
    /**Método responsável por criar uma entrevista.
     *@param df - instancia DataFactory.
     * @return entrevista - Objeto entrevista.
     */
    public Entrevista criarEntrevista(DataFactory df){
        entrevista = entrevistaData.criaEntrevista(df);
        return entrevista;
    }
    
    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Entrevista de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdEnt - quantidade de processos.
     */
    public void criaEntrevistaRadom(DataFactory df,Integer QtdEnt){
     for (int i = 0; i < QtdEnt;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            pn.setEntrevista(criarEntrevista(df));
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
                salvaEstadoNotificacao(ae);
            }                                 
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,2));
            salvarProcesso(pn);
        }
    }
    
    /**Método responsável por criar uma Captacao.
     * @param df - instancia DataFactory.
     * @return captacao - Objeto Captacao.
     */
    public Captacao criaCaptacao(DataFactory df){
        listCaptador = captadorRepository.findAll();
        captacao = captacaoData.criarCaptacao(df, df.getItem(listCaptador));
        return captacao;
    } 
    
    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Captacao de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdCap - quantidade de processos.
     */
    public void criaCaptacaoRadom(DataFactory df,Integer QtdCap){
     for (int i = 0; i < QtdCap;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,3));
            
            pn.setEntrevista(criarEntrevista(df));
            pn.setCaptacao(criaCaptacao(df));
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
                salvaEstadoNotificacao(ae);
            } 
            
            salvarProcesso(pn);
        }
    }
    
    private static Date removeDias(Date date, Integer dias) {  
            GregorianCalendar gc = new GregorianCalendar();  
            gc.setTime(date);  
            gc.set(Calendar.DATE, gc.get(Calendar.DATE) - dias);  
            return gc.getTime();  
    } 
}
