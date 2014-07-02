/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TipoNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class TipoNaoDoacaoData {
    @Autowired
    private TipoNaoDoacaoRepository tipoNaoDoacaoRepository;
    @Autowired
    private Factory fabrica;
    
    private TipoNaoDoacao tipoNaoDoacao;
    private Listas list = Listas.INSTANCE;
    private List<String> listTipoNaoDoacao;
    
    public void criaTipoNaoDoacao(){
        listTipoNaoDoacao = list.getListTipoNaoDoacao();
        for (String s : listTipoNaoDoacao){
            tipoNaoDoacao = fabrica.criaObjeto(TipoNaoDoacao.class);
            tipoNaoDoacao.setNome(s);
            tipoNaoDoacaoRepository.save(tipoNaoDoacao);
        }       
    }
    
}
