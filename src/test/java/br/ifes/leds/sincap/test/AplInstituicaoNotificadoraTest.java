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
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplInstituicaoNotificadora;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aleao
 */
public class AplInstituicaoNotificadoraTest extends AbstractionTest{
    
    @Autowired
    private AplInstituicaoNotificadora aplInstituicaoNotificadora;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;
    
    private Endereco endereco;
    private InstituicaoNotificadora instituicao;
    private Telefone telefone;
    
     /**
     * Metodo que responsavel por criar os objetos nescessários na realização dos testes.
     */
    @Before
    public void criaObjetoBancoOlhos(){
        //Objetos;
        instituicao = new InstituicaoNotificadora();
        endereco = new Endereco();
        telefone = new Telefone();
        
        //Preenchendo o objeto BancoOlhos.
        instituicao.setCnes("cnes");
        instituicao.setEmail("instituicao@notificadora.com.br");
        instituicao.setFantasia("Instituição Notificadora Teste");
        instituicao.setNome("Instituição Notificadora Teste");
        
        //Endereco
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setNumero("45");
        endereco.setComplemento("Casa");
        endereco.setCep("29090668");
        instituicao.setEndereco(endereco); 
        enderecoRepository.save(endereco);
        
        //Telefone
        telefone.setNumero("22222222"); 
        instituicao.setTelefone(telefone); 
        telefoneRepository.save(telefone);
        
        //Salva o objeto Instituição.
        instituicaoNotificadoraRepository.save(instituicao);
    }
    
    /**
     * Método para testar a obtenção de todas instituições notificadoras existentes.
     */
    @Test
    public void obterTodasInstituicoesNotificadoras(){
        Assert.assertNotNull(aplInstituicaoNotificadora.obterTodasInstituicoesNotificadoras());
    }
    /**
     * Método para testar a obtenção de uma instituição notificadora pelo seu id.
     */
    @Test
    public void obter(){
        Assert.assertNotNull(aplInstituicaoNotificadora.obter(instituicao.getId()));
    }
}
