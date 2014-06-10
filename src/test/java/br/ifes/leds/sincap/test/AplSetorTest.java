/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorEmUsoException;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplSetor;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**Classe que realiza o teste nos métodos criados na classe AplSetor.
 *
 * @author aleao
 * 
 * @version 1.0
 * 
 * @since 04/06/2014
 */

public class AplSetorTest extends AbstractionTest {
   
    @Autowired
    private AplSetor aplSetor;
    
    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private AplEndereco aplEndereco;
    
    @Autowired
    private AplHospital aplHospital;
    
    @Autowired
    private SetorRepository setorRepository;
    
    private Hospital hospital;
    private Telefone telefone;
    private Endereco endereco;
    private Setor setor;
        
    /**
     * Metodo que responsavel por criar os objetos nescessários na realização dos testes.
     */
    @Before
    public void criaObjetoSetor() {
         /*Criação dos objetos*/
        hospital =  new Hospital();
        telefone = new Telefone();
        endereco = new Endereco();
        setor = new Setor();
        
        
        /*Preenche a aba dados Gerais*/
        
        hospital.setNome("Hospital Test 1");
        hospital.setFantasia("Hospital Test 1 Fantasia");
        hospital.setCnes("123456789");
        hospital.setSigla("HT1");
        telefone.setNumero("11111111");        
        hospital.setTelefone(telefone);
        hospital.setEmail("hospitaltest1@saude.com.br");
        
        /*Preenche a aba Endereço*/        
        endereco.setCep("12345678");
        endereco.setEstado(aplEndereco.obterEstadosPorID(new Long(1)));
        endereco.setCidade(aplEndereco.obterCidadePorID(new Long(1)));
        endereco.setBairro(aplEndereco.obterBairroPorID(new Long(1)));
        endereco.setLogradouro("Rua do Hospital Test 1");
        endereco.setNumero("123");
        endereco.setComplemento("Complemento do Hospital Test 1");
        hospital.setEndereco(endereco);
        
        aplHospital.cadastrar(hospital);
        
        /*Adicionando um setor de Teste*/
        
        setor.setNome("Setor Teste"); 
        setorRepository.save(setor);
        /*Vinculando o setor ao hospital.*/
        aplSetor.addHospital(hospital, setor.getId());
        
    }
    
    /** Método para testar a busca dos setores de um hospital passando um objeto hospital como parametro.
    */
    @Test
    public void buscarSetorHospital(){                
        
        List<Setor> listSetor = aplSetor.buscar(hospital);
        
        for (Setor s : listSetor) {
            if (s.getNome().equals("Setor Teste")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    
    /** Método para testar a busca dos setores de um hospital passando o id do hospital como parametro.
    */
    
    @Test
    public void buscarSetorHospitalId(){
        List<Setor> listSetor = aplSetor.buscar(hospital.getId());
        
        for (Setor s : listSetor) {
            if (s.getNome().equals("Setor Teste")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
        
    /** Método para testar a adição de um setor a um hospital.
     * @throws br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException
    */   
    @Test
    public void adicionaSetorHospital() throws SetorExistenteException{
        /*Adicionando um novo setor*/
        Setor novoSetor = new Setor();
        novoSetor.setNome("Novo Setor 2"); 
        aplSetor.adicionar(novoSetor);
        
        /*Adicionando o novo setor ao hospital*/
        aplSetor.addHospital(hospital, novoSetor.getId());
        
        /*Verifica se o setor foi adicionado ao hostpital*/
        List<Setor> listSetor = aplSetor.buscar(hospital.getId());
        
        for (Setor s : listSetor) {
            if (s.getNome().equals("Novo Setor 2")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }        
    }
    
    /** Método para testar a remover de um setor a um hospital.
     * @throws br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException
    */   
    @Test
    public void removeSetorHospital() throws SetorExistenteException{
        /*Adicionando um novo setor*/
        Setor novoSetor = new Setor();
        novoSetor.setNome("Novo Setor Teste"); 
        aplSetor.adicionar(novoSetor);
        
        /*Adicionando o novo setor ao hospital*/
        aplSetor.addHospital(hospital, novoSetor.getId());
        
        /*Verifica se o setor foi adicionado ao hostpital*/
        List<Setor> listSetor = aplSetor.buscar(hospital.getId());
        
        for (Setor s : listSetor) {
            if (s.getNome().equals("Novo Setor Teste")) {
                /*Se o setor foi adicionado, este é removido.*/
                aplSetor.removeHospital(hospital, s.getId());
                Assert.assertSame(null, s.getId());
            } else {
                Assert.assertTrue(false);
            }
        }        
    }
    
    /** Método para testar a busca de um setor pelo seu id.
    */
    @Test
    public void buscarSetorId(){
        Setor setorBusca = aplSetor.buscarSetor(setor.getId());        
        Assert.assertSame(setorBusca.getNome(), "Setor Teste");        
    }
    
    /** Método para testar a adição de setor.
     * @throws br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException
    */
    @Test
    public void adicionaSetor() throws SetorExistenteException{
        Setor novoSetor = new Setor();
        novoSetor.setNome("Novo Setor"); 
        aplSetor.adicionar(novoSetor);
        
        Assert.assertNotSame(0, novoSetor.getId());                
    }
    
    /** Método para testar a busca de uma lista com todos os setores existentes.   
     */
    @Test
    public void obterListaSetores(){
        List<Setor> listaSetores = aplSetor.obter();
        Assert.assertFalse(listaSetores.isEmpty());
    }
    
    /** Método para testar a exclusão de um setor.
     * @throws br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorEmUsoException
    */
    @Test
    public void removerSetor() throws SetorEmUsoException{
        aplSetor.excluir(setor.getId());
        Setor setorRemovido = aplSetor.buscarSetor(setor.getId());
        Assert.assertNull(setorRemovido);
            
    }
    
}
