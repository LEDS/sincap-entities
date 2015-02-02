/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CaptacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;
import static br.ifes.leds.reuse.utility.Utility.hoje;

/**
 * Classe para a criação de objetos Captacao randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class CaptacaoData {
    @Autowired
    private CaptacaoRepository captacaoRepository;


    /**
     * Método responsável por salvar um objeto Captacao no banco de dados.
     *
     * @param c - Objeto Captacao.
     */
    public void salvarCaptacao(Captacao c) {
        captacaoRepository.save(c);
    }

    /**
     * Método responsável por criar Objetos Captacao randomico.
     *
     * @param df - instancia DataFactory.
     * @param c  - objeto Captador.
     * @return captacao - objeto Captacao Randomico.
     */
    public Captacao criarCaptacao(DataFactory df, Captador c) {
        Captacao captacao = criaObjeto(Captacao.class);

        captacao.setCaptacaoRealizada(true);

        if (captacao.isCaptacaoRealizada()) {
            captacao.setDataCaptacao(hoje());
        }
        captacao.setDataCadastro(hoje());
        captacao.setCaptador(c);
        captacao.setComentario("Comentario " + df.getRandomChar());

        return captacao;
    }
}

