/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CaptacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import java.util.Calendar;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos Captacao randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class CaptacaoData {
    @Autowired
    private CaptacaoRepository captacaoRepository;
    @Autowired
    private CaptadorRepository captadorRepository;
    @Autowired
    private Factory fabrica;
    
    private Captacao captacao;
    private List<Captador> listCaptador;
    private Calendar dataCaptacao;
    private Calendar dataCadastro;
    
    
    /** Método responsável por salvar um objeto Captacao no banco de dados.
     * @param c - Objeto Captacao. 
     */
    public void salvarCaptacao(Captacao c){
        captacaoRepository.save(c);
    }
    
    /**Método responsável por criar Objetos Captacao randomico.
     * @param df - instancia DataFactory.
     * @param c - objeto Captador. 
     * @return captacao - objeto Captacao Randomico.
     */
    public Captacao criarCaptacao(DataFactory df,Captador c){
        captacao = fabrica.criaObjeto(Captacao.class);
        dataCadastro = Calendar.getInstance();
        dataCaptacao = Calendar.getInstance();
        
        dataCadastro.setTime(df.getDateBetween(df.getDate(2000, 01, 01), df.getDate(2014, 12, 30)));
        captacao.setDataCadastro(dataCadastro);
        dataCaptacao.setTime(df.getDateBetween(dataCaptacao.getTime(), df.getDate(2014, 12, 30)));
        captacao.setDataCaptacao(dataCaptacao);        
        captacao.setCaptacaoRealizada(df.chance(50));
        captacao.setCaptador(c);
        captacao.setComentario("Comentario "+df.getRandomChar());
        
        return captacao;
    }
}

