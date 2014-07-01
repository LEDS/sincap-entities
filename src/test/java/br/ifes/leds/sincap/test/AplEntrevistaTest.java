/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.PacienteData;
import org.fluttercode.datafactory.impl.DataFactory;
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
    @Autowired
    private DataFactory dataFactory;
    
    private PacienteData pacienteData;
    @Autowired
    private Factory factory;
    //Preencher um DTO e uma Entrevista
    @Before
    public void before()
    {
        Entrevista entrevista = new Entrevista();
    }
}
