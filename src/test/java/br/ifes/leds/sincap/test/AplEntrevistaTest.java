/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ResponsavelDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.TestemunhaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplEntrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.ResponsavelData;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.TestemunhaData;

/**
 *
 * @author Breno Grillo
 */
public class AplEntrevistaTest extends AbstractionTest{
    
    @Autowired
    private AplEntrevista aplEntrevista;
    @Autowired
    private AplEndereco aplEndereco;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;
    @Autowired
    private ResponsavelData responsavelData;
    @Autowired
    private TestemunhaData testemunhaData;
    @Autowired
    private DataFactory dataFactory;
    @Autowired
    private Factory factory;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    // TODO Fazer testes de obter entrevista. 
//    private final Method getNomeResponsavelDTO;
//    private final Method getNomeTestemunhaDTO;
    
    private EntrevistaDTO entrevistaDTO;
    private ResponsavelDTO responsavelDTO;
    private TestemunhaDTO testemunhaDTO1;
    private TestemunhaDTO testemunhaDTO2;
    
//    public AplEntrevistaTest() throws NoSuchMethodException, SecurityException {
//        getNomeResponsavelDTO = ResponsavelDTO.class.getDeclaredMethod("getNome");
//        getNomeTestemunhaDTO = TestemunhaDTO.class.getDeclaredMethod("getNome");
//    }
    
    @Before
    public void before()
    {
        this.entrevistaDTO = factory.criaObjeto(EntrevistaDTO.class);
        this.responsavelDTO = mapper.map(responsavelData.criarResponsavel(dataFactory),
                ResponsavelDTO.class);
        this.testemunhaDTO1 = mapper.map(testemunhaData.criarTestemunha(dataFactory),
                TestemunhaDTO.class);
        this.testemunhaDTO2 = mapper.map(testemunhaData.criarTestemunha(dataFactory),
                TestemunhaDTO.class);
        
        preencherDadosEntrevista();
    }
    
    private void preencherDadosEntrevista()
    {
        Funcionario funcionario = funcionarioRepository.findAll().get(0);
        
        this.entrevistaDTO.setDataEntrevista(new GregorianCalendar(2014, 5, 27, 18, 30));
        this.entrevistaDTO.setDataCadastro(new GregorianCalendar(2014, 5, 27, 22, 55));
        this.entrevistaDTO.setDoacaoAutorizada(true);
        this.entrevistaDTO.setFuncionario(funcionario.getId());
        this.entrevistaDTO.setResponsavel(responsavelDTO);
        this.entrevistaDTO.setTestemunha1(testemunhaDTO1);
        this.entrevistaDTO.setTestemunha2(testemunhaDTO2);
    }
    
    @Test
    public void salvarEntrevistaTest() throws ViolacaoDeRIException
    {
        aplEntrevista.salvarEntrevista(this.entrevistaDTO);
        
        EntrevistaDTO entrevistaTest = aplEntrevista.getAllEntrevistas().get(0);
        
        Assert.assertNotNull(entrevistaTest);
        Assert.assertNotNull(entrevistaTest.getResponsavel().getId());
        Assert.assertNotNull(entrevistaTest.getTestemunha1().getId());
        Assert.assertNotNull(entrevistaTest.getTestemunha2().getId());
        
        Assert.assertEquals(this.entrevistaDTO.getResponsavel().getEndereco().getCep(), 
                entrevistaTest.getResponsavel().getEndereco().getCep());
        Assert.assertEquals(this.entrevistaDTO.getTestemunha1().getNome(), 
                entrevistaTest.getTestemunha1().getNome());
        Assert.assertEquals(this.entrevistaDTO.getTestemunha2().getNome(), 
                entrevistaTest.getTestemunha2().getNome());
    }
}
