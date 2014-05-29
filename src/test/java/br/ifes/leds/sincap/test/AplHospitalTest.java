package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeEmUsoException;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeExistenteException;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoTelefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplSetor;
import java.util.HashSet;
import java.util.Set;

public class AplHospitalTest extends AbstractionTest {

    @Autowired
    AplHospital aplHospital;
    @Autowired
    AplEndereco aplEndereco;
    @Autowired
    AplSetor aplSetor;

    @Test
    public void cadastrarHospital() {
        
        Hospital hospital = new Hospital();

        /*preenchendo aba dados gerais*/
        hospital = preencherAbaDadosGerais(hospital);
        /*preenchendo aba endereco*/
        hospital = preencherAbaEndereco(hospital);
        /*preechendo aba setores*/
        hospital = preencherAbaSetores(hospital);

        aplHospital.cadastrar(hospital);
        Assert.assertNotSame(0, hospital.getId());
    }

    @Test
    public void excluirMotivo() throws MotivoInviabilidadeExistenteException, MotivoInviabilidadeEmUsoException {
//		MotivoInviabilidade motivoInviabilidade = new MotivoInviabilidade();
//		List<TipoMotivoInviabilidade> tipoMotivo = apl.getTipoMotivoInviabilidade();
//		
//		motivoInviabilidade.setNome("Motivo para excluir");
//		motivoInviabilidade.setTipoMotivo(tipoMotivo.get(0));
//		
//		apl.adicionar(motivoInviabilidade);		
//		Assert.assertNotSame(0, motivoInviabilidade.getId());
//		
//		//apl.excluir(motivoInviabilidade);
//		MotivoInviabilidade motivoInviabilidadeTeste = apl.buscar("Motivo para excluir");
//		Assert.assertNull(motivoInviabilidadeTeste);
    }

//	@Test
//	public void testaMetodo()
//	{
//		if(notif.findByMotivoInviabilidade(motivoInviabilidade) == null)
//			System.out.println("nulo");
//		else
//			if(notif.findByMotivoInviabilidade(motivoInviabilidade).isEmpty())
//			System.out.println("vazio");
//			else
//				System.out.println("cheia");
//			
//	}
////	
//	

    private Hospital preencherAbaDadosGerais(Hospital hospital) {
       
        Set<Telefone> telefones = new HashSet<Telefone>();
        Telefone telefone = new Telefone();
        
        hospital.setNome("Hospital Test 1");
        hospital.setFantasia("Hospital Test 1 Fantasia");
        hospital.setCnes("123456789");
        hospital.setSigla("HT1");

        telefone.setNumero("11111111");
        
        hospital.setTelefone(telefone);
        hospital.setEmail("hospitaltest1@saude.com.br");
        
        return hospital;
        
    }

    private Hospital preencherAbaSetores(Hospital hospital) {
        
        Endereco endereco = new Endereco();
        
        endereco.setCep("12345678");
        endereco.setEstado(aplEndereco.obterEstadosPorID(new Long(1)));
        endereco.setCidade(aplEndereco.obterCidadePorID(new Long(1)));
        endereco.setBairro(aplEndereco.obterBairroPorID(new Long(1)));
        endereco.setLogradouro("Rua do Hospital Test 1");
        endereco.setNumero("123");
        endereco.setComplemento("Complemento do Hospital Test 1");
        hospital.setEndereco(endereco);
        
        return hospital;
        
    }

    private Hospital preencherAbaEndereco(Hospital hospital) {
        
        Setor setor = new Setor();
        
        setor = aplSetor.buscarSetor(new Long(1));
        hospital.addSetor(setor);
        setor = aplSetor.buscarSetor(new Long(2));
        hospital.addSetor(setor);
        
        return hospital;
        
    }
}
