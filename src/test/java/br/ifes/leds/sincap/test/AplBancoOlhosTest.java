/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.DocumentoComFotoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplBancoOlhos;

/**
 *
 * @author aleao
 */
public class AplBancoOlhosTest extends AbstractionTest{
    @Autowired
    private AplBancoOlhos aplBancoOlhos;
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
    private DocumentoComFotoRepository documentoComFotoRepository;
    
    private Endereco endereco;
    private BancoOlhos banco;
    private Telefone telefone;
    
     /**
     * Metodo que responsavel por criar os objetos nescessários na realização dos testes.
     */
    @Before
    public void criaObjetoBancoOlhos(){
        //Objetos;
        banco = new BancoOlhos();
        endereco = new Endereco();
        telefone = new Telefone();
        
        //Preenchendo o objeto BancoOlhos.
        banco.setCnes("cnes");
        banco.setEmail("banco@olhos.com.br");
        banco.setFantasia("Banco de Olhos Teste");
        banco.setNome("Banco de Olhos Teste");
        
        //Endereco
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setNumero("45");
        endereco.setComplemento("Casa");
        endereco.setCep("29090668");
        banco.setEndereco(endereco); 
        enderecoRepository.save(endereco);
        
        //Telefone
        telefone.setNumero("22222222"); 
        banco.setTelefone(telefone); 
        telefoneRepository.save(telefone);
    }
    
    /**
     * Metodo que responsavel por testar o cadastro de um novo Banco de Olhos.
     */
    @Test
    public void cadastrarBancoOlhos(){
        aplBancoOlhos.cadastrar(banco);
        Assert.assertNotSame(0, banco.getId());
    }
    
    /**
     * Metodo que responsavel por Atualizar um novo banco de olhos.
     */
    @Test
    public void atualizarBancoOlhos(){
        aplBancoOlhos.cadastrar(banco);
        banco.setNome("Banco de Olhos Teste Update");
        aplBancoOlhos.update(banco);
        Assert.assertNotSame("Banco de Olhos Teste", banco.getNome());
    }
    
    /**
     * Metodo que responsavel por remover um banco de olhos pelo seu id.
     */
    @Test
    public void removerBancoOlhos(){
        aplBancoOlhos.cadastrar(banco);
        aplBancoOlhos.delete(banco.getId());
        Assert.assertNull(aplBancoOlhos.obter(banco.getId()));
    }
    
    /**
     * Metodo que responsavel por remover um banco de olhos.
     */
    @Test
    public void removerObjetoBancoOlhos(){
        aplBancoOlhos.cadastrar(banco);
        aplBancoOlhos.delete(banco);
        Assert.assertNull(aplBancoOlhos.obter(banco.getId()));
    }
    
    /**
     * Metodo que responsavel por obter um banco de olhos pelo seu nome.
     */
    @Test
    public void obterBancoOlhosNome(){
        aplBancoOlhos.cadastrar(banco);
        Assert.assertSame("Banco de Olhos Teste", aplBancoOlhos.obter(banco.getId()).getNome());
    }
    
    /**
     * Metodo que responsavel por obter um banco de olhos pelo seu id.
     */
    @Test
    public void obterBancoOlhosId(){
        aplBancoOlhos.cadastrar(banco);
        Assert.assertNotSame(0, aplBancoOlhos.obter(banco.getId()));
    }
    
    
    /**
     * Metodo que responsavel por obter uma lista com todos os banco de olhos existentes.
     */
    @Test
    public void obterTodos(){
        aplBancoOlhos.cadastrar(banco);
        Assert.assertNotNull(aplBancoOlhos.obter());
    }
    
    /**
     * Metodo que responsavel por obter a quantidade de banco de olhos existentes.
     */
    @Test
    public void obterQuantidade(){
        aplBancoOlhos.cadastrar(banco);
        Assert.assertNotSame(0,aplBancoOlhos.quantidade());
    }
    
}
