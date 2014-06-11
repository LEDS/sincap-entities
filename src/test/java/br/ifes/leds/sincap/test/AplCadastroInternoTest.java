/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cln.cgt.AplCadastroInterno;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aleao
 */
public class AplCadastroInternoTest extends AbstractionTest{
    @Autowired
    private AplCadastroInterno aplCadastroInterno;
    
    /**
     * Método responsável por testar a obtenção de um setor pelo seu id.
     */
    @Test
    public void obterSetorId(){
        Assert.assertNotSame(0,aplCadastroInterno.obterSetorPorId(new Long(1)));
    }
    
}
