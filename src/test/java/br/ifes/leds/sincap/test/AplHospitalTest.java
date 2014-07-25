package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplSetor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AplHospitalTest extends AbstractionTest {

    @Autowired
    private AplHospital aplHospital;
    @Autowired
    private AplEndereco aplEndereco;
    @Autowired
    private AplSetor aplSetor;
    
    private Hospital hospital;
    private Telefone telefone;
    private Endereco endereco;
    private Setor setor;

    
    /** Método para preencher os dados nescessários para a criação de um Hospital.
    */
    @Before
    public void criaObjetoHospital() {
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
        
        /*Preenche a aba Setor*/  
        setor = aplSetor.buscarSetor(new Long(1));
    }
    
    /** Método para testar o cadastro de um novo hospital.
    */
    @Test
    public void cadastrarHospital() {        
        aplHospital.cadastrar(hospital);
        Assert.assertNotSame(0, hospital.getId());
    }
    
    /** Método para testar a atualização de dados cadastrais de um hospital.
    */
    @Test
    public void atualizarHospital() {        
        String nomeHosp = hospital.getNome();        
        hospital.setNome("Hospital Teste Update");
        aplHospital.update(hospital);         
        Assert.assertNotSame(nomeHosp, hospital.getNome());
    }
    
    /** Método para testar a busca de um hospital pelo nome.
    */
    @Test
    public void obterString() { 
        aplHospital.cadastrar(hospital);
        Assert.assertSame("Hospital Test 1", aplHospital.obter(hospital.getNome()).getNome());
        
    }
    
    /** Método para testar a busca de um hospital pelo seu id.
    */
    @Test
    public void obterLong() { 
        aplHospital.cadastrar(hospital);
        Assert.assertSame("Hospital Test 1", aplHospital.obter(hospital.getId()).getNome());
    }
    
    /** Método para testar a busca da lista de hospitais existentes.
    */
    @Test
    public void obterLista() {    
        aplHospital.cadastrar(hospital);
        List<Hospital> listHospital = aplHospital.obter();        
        Assert.assertFalse(listHospital.isEmpty());
    }
    
    /** Método para testar a busca da quantidade de hospitais existentes.
    */
    @Test
    public void quantidadeHospital() {   
        aplHospital.cadastrar(hospital);
        Long qtdHospital = aplHospital.quantidade();        
        Assert.assertNotNull(qtdHospital);
    }
    
    /** Método para testar a adição de um setor em um hospital.
    */
    @Test
    public void adicionarSetor() {    
        aplHospital.cadastrar(hospital);
        aplHospital.addSetor(setor,hospital.getId());  
        for(Setor s : hospital.getSetores()){
            if(s.getNome().equals(setor.getNome())){
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    
    /** Método para testar a remoção de um setor em um hospital.
    */
    
    @Test
    public void removeSetor() { 
        aplHospital.cadastrar(hospital);
        aplHospital.addSetor(setor,hospital.getId());
        aplHospital.removerSetor(setor,hospital.getId());         
        Assert.assertTrue(hospital.getSetores().isEmpty());
    }
}

