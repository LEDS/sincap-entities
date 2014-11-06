/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum.*;

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
    private CausaNaoDoacaoRepository causaNaoDoacaoRepository;
    @Autowired
    private NotificadorRepository notificadorRepository;
    @Autowired
    private CaptadorRepository captadorRepository;
    @Autowired
    private AtualizacaoEstadoRepository atualizacaoEstadoRepository;
    @Autowired
    private ObitoData obitoData;
    @Autowired
    private NotificadorData notificadorData;
    @Autowired
    private EntrevistaData entrevistaData;
    @Autowired
    private CaptacaoData captacaoData;



    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Óbito de forma randomica.
     * @param df - instancia DataFactory.
     * @param qtdAna - quantidade de processos.
     */
    @SuppressWarnings("unused")
    public void criarAnaliseObitoRandom(DataFactory df,Integer qtdAna){
        for (int i = 0; i < qtdAna;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
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
        AtualizacaoEstado atualizacaoEstado = criaObjeto(AtualizacaoEstado.class);
        
        atualizacaoEstado.setFuncionario(pn.getNotificador());
        
        if (etapa == 1) {
            atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
            atualizacaoEstado.setEstadoNotificacao(AGUARDANDOANALISEOBITO);
        } 
        else {
            if(etapa == 2){
                    atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
                    atualizacaoEstado.setEstadoNotificacao(AGUARDANDOANALISEENTREVISTA);
               }
                else {
                    if(etapa == 3){
                        atualizacaoEstado.setDataAtualizacaos(pn.getDataAbertura());
                        atualizacaoEstado.setEstadoNotificacao(AGUARDANDOANALISECAPTACAO);
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

        ProcessoNotificacao processoNotificacao = criaObjeto(ProcessoNotificacao.class);
        Calendar dataAbertura = Calendar.getInstance();
        Obito obito = obitoData.criaObito(df);
        Date dataIni = removeDias(obito.getDataObito().getTime());

        
        processoNotificacao.setArquivado(false);
        List<Notificador> listNotificador = notificadorRepository.findAll();
        processoNotificacao.setNotificador(df.getItem(listNotificador));
        processoNotificacao.setObito(obito);
        dataAbertura.setTime(df.getDateBetween(dataIni, obito.getDataObito().getTime()));
        processoNotificacao.setDataAbertura(dataAbertura);
        processoNotificacao.setCodigo(df.getNumberText(8));
        processoNotificacao.setEntrevista(null);
        
        return processoNotificacao;
    }

    /**Método responsável por criar um processo de notificação randomico até a
     * etapa de Analise de Óbito.
     * @param df - instancia DataFactory.
     * @return processoNotificacao - objeto ProcessoNotificacao.
     */
    public ProcessoNotificacao criarAnaliseObitoHospital(DataFactory df,Hospital hospital){

        ProcessoNotificacao processoNotificacao = criaObjeto(ProcessoNotificacao.class);
        Calendar dataAbertura = Calendar.getInstance();
        Obito obito = obitoData.criaObitoHospital(df, hospital);
        Date dataIni = removeDias(obito.getDataObito().getTime());
        Notificador notificador = notificadorData.criaNotificador(df);
        Set<InstituicaoNotificadora> set = new HashSet<>();
        set.add(hospital);
        notificador.setInstituicoesNotificadoras(set);
        notificadorData.salvarNotificador(df,notificador);

        processoNotificacao.setArquivado(false);
        processoNotificacao.setNotificador(notificador);
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
        processoNotificacaoRepository.save(pn);
    }
    
    /**Método responsável por criar uma entrevista.
     *@param df - instancia DataFactory.
     * @return entrevista - Objeto entrevista.
     */
    public Entrevista criarEntrevista(DataFactory df){
        return entrevistaData.criaEntrevista(df);
    }
    
    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Entrevista de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdEnt - quantidade de processos.
     */
    @SuppressWarnings("unused")
    public void criaEntrevistaRadom(DataFactory df,Integer QtdEnt){
     for (int i = 0; i < QtdEnt;i++){
            ProcessoNotificacao pn = criarAnaliseObito(df);
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataCadastro = Calendar.getInstance();
            Calendar dataEntrevista = Calendar.getInstance();

            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            Entrevista entrevista = criarEntrevista(df);
            dataCadastro.setTime(df.getDateBetween(pn.getObito().getDataObito().getTime(),dataAtual.getTime()));
            entrevista.setDataCadastro(dataCadastro);
            dataEntrevista.setTime(df.getDateBetween(entrevista.getDataCadastro().getTime(),dataAtual.getTime()));
            entrevista.setDataEntrevista(dataEntrevista);
            pn.setEntrevista(entrevista);
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
            }
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn,2));
            salvarProcesso(pn);
        }
    }

    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Entrevista com entrevista autorizada de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdEnt - quantidade de processos.
     */
    @SuppressWarnings("unused")
    public void criaEntrevistaAutorizadaRadom(DataFactory df,Hospital hospital,Integer QtdEnt,Calendar datIni,Calendar datFim){
        for (int i = 0; i < QtdEnt;i++){
            ProcessoNotificacao pn = criarAnaliseObitoHospital(df, hospital);
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataCadastro = Calendar.getInstance();
            Calendar dataEntrevista = Calendar.getInstance();

            pn.getDataAbertura().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataCadastro().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataObito().setTime((df.getDateBetween(pn.getObito().getDataCadastro().getTime(),datFim.getTime())));

            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            Entrevista entrevista = criarEntrevista(df);
            dataCadastro.setTime(df.getDateBetween(pn.getObito().getDataObito().getTime(),datFim.getTime()));
            entrevista.setDataCadastro(dataCadastro);
            dataEntrevista.setTime(df.getDateBetween(entrevista.getDataCadastro().getTime(),datFim.getTime()));
            entrevista.setDataEntrevista(dataEntrevista);
            entrevista.setDoacaoAutorizada(true);
            entrevista.setEntrevistaRealizada(true);
            pn.setEntrevista(entrevista);
            pn.setHistorico(listAtualizacao);

            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
            }
            salvaEstadoNotificacao(AtualizaEstadoNotificacao(pn, 2));
            salvarProcesso(pn);
        }
    }

    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Entrevista com recusa de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdEnt - quantidade de processos.
     */
    @SuppressWarnings("unused")
    public void criaEntrevistaRecusadaRadom(DataFactory df,Hospital hospital,Integer QtdEnt,Calendar datIni,Calendar datFim){
        for (int i = 0; i < QtdEnt;i++){
            ProcessoNotificacao pn = criarAnaliseObitoHospital(df, hospital);
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataCadastro = Calendar.getInstance();
            Calendar dataEntrevista = Calendar.getInstance();
            List<CausaNaoDoacao> listCausa = causaNaoDoacaoRepository.findAll();

            pn.getDataAbertura().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataCadastro().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataObito().setTime((df.getDateBetween(pn.getObito().getDataCadastro().getTime(),datFim.getTime())));

            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            Entrevista entrevista = criarEntrevista(df);
            dataCadastro.setTime(df.getDateBetween(pn.getObito().getDataObito().getTime(),datFim.getTime()));
            entrevista.setDataCadastro(dataCadastro);
            dataEntrevista.setTime(df.getDateBetween(entrevista.getDataCadastro().getTime(), datFim.getTime()));
            entrevista.setDataEntrevista(dataEntrevista);
            entrevista.setDoacaoAutorizada(false);
            entrevista.setEntrevistaRealizada(true);
            pn.setEntrevista(entrevista);
            pn.setCausaNaoDoacao(df.getItem(listCausa));
            pn.setHistorico(listAtualizacao);

            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
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
        List<Captador> listCaptador = captadorRepository.findAll();
        return captacaoData.criarCaptacao(df, df.getItem(listCaptador));
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
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataCadastro = Calendar.getInstance();
            Calendar dataEntrevista = Calendar.getInstance();
            
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,3));

            Entrevista entrevista = criarEntrevista(df);
            dataCadastro.setTime(df.getDateBetween(pn.getObito().getDataObito().getTime(),dataAtual.getTime()));
            entrevista.setDataCadastro(dataCadastro);
            dataEntrevista.setTime(df.getDateBetween(entrevista.getDataCadastro().getTime(),dataAtual.getTime()));
            entrevista.setDataEntrevista(dataEntrevista);
            pn.setEntrevista(entrevista);
            pn.setCaptacao(criaCaptacao(df));
            pn.setHistorico(listAtualizacao);
            
            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
            }
            
            salvarProcesso(pn);
        }
    }

    /**Método responsável por criar processo de notificação randomico até a
     * etapa de Analise de Captacao de forma randomica.
     * @param df - instancia DataFactory.
     * @param QtdCap - quantidade de processos.
     */
    public void criaCaptacaoRealizadaRadom(DataFactory df,Hospital hospital,Integer QtdCap,Calendar datIni,Calendar datFim){
        for (int i = 0; i < QtdCap;i++){
            ProcessoNotificacao pn = criarAnaliseObitoHospital(df,hospital);
            List<AtualizacaoEstado> listAtualizacao = new ArrayList<>();
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataCadastro = Calendar.getInstance();
            Calendar dataEntrevista = Calendar.getInstance();

            pn.getDataAbertura().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataCadastro().setTime(df.getDateBetween(datIni.getTime(),datFim.getTime()));
            pn.getObito().getDataObito().setTime((df.getDateBetween(pn.getObito().getDataCadastro().getTime(),datFim.getTime())));

            listAtualizacao.add(AtualizaEstadoNotificacao(pn,1));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,2));
            listAtualizacao.add(AtualizaEstadoNotificacao(pn,3));

            Entrevista entrevista = criarEntrevista(df);
            dataCadastro.setTime(df.getDateBetween(pn.getObito().getDataObito().getTime(),dataAtual.getTime()));
            entrevista.setDataCadastro(dataCadastro);
            dataEntrevista.setTime(df.getDateBetween(entrevista.getDataCadastro().getTime(), dataAtual.getTime()));
            entrevista.setDataEntrevista(dataEntrevista);
            entrevista.setDoacaoAutorizada(true);
            entrevista.setEntrevistaRealizada(true);
            pn.setEntrevista(entrevista);

            Captacao captacao = criaCaptacao(df);
            captacao.setCaptacaoRealizada(true);
            captacao.setDataCadastro(removeHora(captacao.getDataCaptacao()));
            captacao.setDataCaptacao(removeHora(captacao.getDataCaptacao()));
            pn.setCaptacao(captacao);
            pn.setHistorico(listAtualizacao);

            for(AtualizacaoEstado ae : listAtualizacao){
                pn.setUltimoEstado(ae);
            }

            salvarProcesso(pn);
        }
    }
    private static Date removeDias(Date date) {
            GregorianCalendar gc = new GregorianCalendar();  
            gc.setTime(date);  
            gc.set(Calendar.DATE, gc.get(Calendar.DATE) - 2);
            return gc.getTime();  
    }

    private static Calendar removeHora(Calendar calendar) {
        calendar.add(Calendar.HOUR_OF_DAY,-2);
        return calendar;
    }
}
