/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class CausaMortisData {
    @Autowired
    private CausaMortisRepository causaMortisRepository;
    @Autowired
    private Factory fabrica;
    
    private CausaMortis causaMortis;
    
    private Listas list = Listas.INSTANCE;
    
    private List<String> listaCausa;
    
    public void criaCausaMortisRandom(DataFactory df, Integer qtdCau){
        for (int i = 0; i < qtdCau; i++) {
            salvarCausaMortis(criaCausaMortis(df));
        }
    }

    public void salvarCausaMortis(CausaMortis cm) {
        causaMortisRepository.save(cm);
    }

    public CausaMortis criaCausaMortis(DataFactory df) {
        causaMortis = fabrica.criaObjeto(CausaMortis.class);
        
        listaCausa = list.getListCausaMortis();
        
        causaMortis.setNome(df.getItem(listaCausa));
        
        return causaMortis;
    }
}
