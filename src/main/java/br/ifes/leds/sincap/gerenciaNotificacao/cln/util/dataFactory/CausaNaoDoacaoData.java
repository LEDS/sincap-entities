/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;

/**Classe para a criação de objetos CausaNaoDoacao randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class CausaNaoDoacaoData {
    @Autowired
    private CausaNaoDoacaoRepository causaNaoDoacaoRepository;
    @Autowired
    private Factory fabrica;
    private CausaNaoDoacao causaNaoDoacao;
    private List<TipoNaoDoacao> listTipoNaoDoacao = new ArrayList<>();
    
    
    /**Método responsável por criar Objetos CausaNaoDoacao randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdNdo - quantidade de objetos a serem criados. 
     */
    public void criaCausaNaoDoacaoRandom(DataFactory df, Integer qtdNdo){
        for (int i = 0; i < qtdNdo; i++){
            salvarCausaNaoDoacao(criaCausaNaoDoacao(df));
        }
    }
    
    /** Método responsável por salvar um objeto CausaNaoDoacao no banco de dados.
     * @param cn - Objeto CausaNaoDoacao. 
     */
    public void salvarCausaNaoDoacao(CausaNaoDoacao cn){
        causaNaoDoacaoRepository.save(cn);
    }
    
     /**Método responsável por criar Objetos CausaNaoDoacao randomico.
     * @param df - instancia DataFactory.
     * @return causaNaoDoacao - objeto CausaNaoDoacao Randomico.
     */
    public CausaNaoDoacao criaCausaNaoDoacao(DataFactory df) {
        causaNaoDoacao = fabrica.criaObjeto(CausaNaoDoacao.class);
        
        causaNaoDoacao.setNome("Causa "+df.getRandomText(5));
        listTipoNaoDoacao.add(TipoNaoDoacao.CONTRAINDICACAO_MEDICA);
        listTipoNaoDoacao.add(TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS);
        listTipoNaoDoacao.add(TipoNaoDoacao.PROBLEMAS_LOGISTICOS);
        listTipoNaoDoacao.add(TipoNaoDoacao.RECUSA_FAMILIAR);
        causaNaoDoacao.setTipoNaoDoacao(df.getItem(listTipoNaoDoacao));
        
        return causaNaoDoacao;
    }
    
}
