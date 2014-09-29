/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos CausaMortis randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class CausaMortisData {
    @Autowired
    private CausaMortisRepository causaMortisRepository;

    private Listas list = Listas.INSTANCE;

    /**Método responsável por criar Objetos CausaMortis randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdCau - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaCausaMortisRandom(DataFactory df, Integer qtdCau){
        for (int i = 0; i < qtdCau; i++) {
            salvarCausaMortis(criaCausaMortis(df));
        }
    }
    
    /** Método responsável por salvar um objeto CausaMortis no banco de dados.
     * @param cm - Objeto CausaMortis. 
     */
    public void salvarCausaMortis(CausaMortis cm) {
        causaMortisRepository.save(cm);
    }

    /**Método responsável por criar Objetos CausaMortis randomico.
     * @param df - instancia DataFactory.
     * @return causaMortis - objeto CausaMortis Randomico.
     */
    public CausaMortis criaCausaMortis(DataFactory df) {
        CausaMortis causaMortis = criaObjeto(CausaMortis.class);

        List<String> listaCausa = list.getListCausaMortis();
        
        causaMortis.setNome(df.getItem(listaCausa));
        
        return causaMortis;
    }
}
