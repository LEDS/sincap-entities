/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplAnalistaCNCDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aleao
 * 
 * @version 1.0
 * 
 * @since 03/06/2014
 */
public class AplAnalistaCNCDOTest extends AbstractionTest{
    
    @Autowired
    AplAnalistaCNCDO aplAnalistaCNCDO;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    
    private Telefone telefone;
    private Endereco endereco;
    private AnalistaCNCDO analista;
    
    /**
     * Metodo que responsavel por criar os objetos nescessários na realização dos testes.
     */
    @Before
    public void criaObjetoAnalista(){
        //Objetos
        analista = new AnalistaCNCDO();
        endereco = new Endereco();
        telefone = new Telefone();
        
        //Dados
        analista.setAtivo(true);
        analista.setCpf("122.818.155.98");
        analista.setDocumentoSocial("1668157");
        analista.setEmail("analista@email.com.br");
        analista.setNome("Analista 1");
        
        //Endereco
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setNumero("45");
        endereco.setComplemento("Casa");
        endereco.setCep("29090668");
        analista.setEndereco(endereco); 
        enderecoRepository.save(endereco);

        //Telefone
        telefone.setNumero("22222222"); 
        analista.setTelefone(telefone); 
        telefoneRepository.save(telefone);
                
        
    }
    /** Método para testar a obtenção de um analista pelo seu Id.
     */
    @Test
    public void obterId(){        
        aplAnalistaCNCDO.salvar(analista);
        analista = aplAnalistaCNCDO.obter(analista.getId());        
        Assert.assertEquals("Analista 1", analista.getNome());
    }
    
     /** Método para testar a obtenção de um analista pelo seu CPF.
     */
    @Test
    public void obterCPF(){        
        aplAnalistaCNCDO.salvar(analista);
        analista = aplAnalistaCNCDO.obter(analista.getCpf());        
        Assert.assertEquals("Analista 1", analista.getNome());
    }
    
    /** Método para testar a inclusão de um analista.
     */
    @Test
    public void salvarAnalista(){
        aplAnalistaCNCDO.salvar(analista);
        Assert.assertNotNull(analista);
        
    }
           
    /** Método para testar a exclusão de um analista.
     */
    @Test
    public void excluirAnalista(){
        aplAnalistaCNCDO.salvar(analista);
        aplAnalistaCNCDO.excluir(analista);
        analista = aplAnalistaCNCDO.obter(analista.getId());        
        Assert.assertNull(analista);
    }
        
}
