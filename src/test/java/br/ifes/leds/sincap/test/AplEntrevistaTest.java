/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplEntrevista;

/**
 *
 * @author Breno Grillo
 */
public class AplEntrevistaTest extends AbstractionTest{
    
    @Autowired
    private AplEntrevista aplEntrevista;
    
    //Preencher um DTO e uma Entrevista
    @Before
    public void before()
    {
        Entrevista entrevista = new Entrevista();
    }
}
