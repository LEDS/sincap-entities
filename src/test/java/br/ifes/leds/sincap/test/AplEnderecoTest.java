package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Estado;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import java.util.List;
import junit.framework.Assert;

public class AplEnderecoTest extends AbstractionTest {

	@Autowired
	private AplEndereco aplEndereco;

	
	@Test
	public void obterEstadoNomePais()
        {
            Assert.assertNotNull(aplEndereco.obterEstadosPorNomePais("Brasil"));
        }
	
	
	/*
	@Test
	public void testObterPais() throws Exception{
		Assert.notNull(this.aplEndereco.obterPaisPorNome("Brasil"));
		Assert.notNull(this.aplEndereco.obterPaisPorNome("Brasil"));
		Assert.notNull(this.aplEndereco.obterPaisPorNome("BRASIL"));
	}
	
	@Test
	public void testObterEstadosPorNomePais() throws Exception{
		Assert.notEmpty(this.aplEndereco.obterEstadosPorNomePais("brasil"));
		Assert.notEmpty(this.aplEndereco.obterEstadosPorNomePais("Brasil"));		
		Assert.notEmpty(this.aplEndereco.obterEstadosPorNomePais("BRASIL"));		
	}
	
	@Test
	public void testObterEstadosPorObjetoPais() throws Exception{
		Pais pais = this.aplEndereco.obterPaisPorNome("Brasil");
		Assert.notEmpty(this.aplEndereco.obterEstadosPorPais(pais.getId()));		
	}
	*/
	@Test
	public void obterCidadesPorEstado() throws Exception{
                List<Estado> estados = aplEndereco.obterEstadosPorNomePais("Brasil");                
                Estado estado = estados.get(0);
		Assert.assertNotNull(this.aplEndereco.obterCidadesPorEstado(estado.getId()));	
	
	}
	/*
	@Test
	public void testObterMunicipiosPorObjetoEstado() throws Exception{
		Pais pais = this.aplEndereco.obterPaisPorNome("Brasil");
		List<Estado> estados = this.aplEndereco.obterEstadosPorPais(pais.getId());
		Assert.notEmpty(this.aplEndereco.obterCidadesPorEstado(estados.get(0).getId()));		
	}
	
	@Test
	public void testObterBairroPorMunicipio() throws Exception{
		Pais pais = this.aplEndereco.obterPaisPorNome("Brasil");
		List<Estado> estados = this.aplEndereco.obterEstadosPorPais(pais.getId());
		List <Cidade> municipios = this.aplEndereco.obterCidadesPorEstado(estados.get(0).getId());
		Assert.notEmpty(this.aplEndereco.obterBairrosPorCidade(municipios.get(0).getId()));
	}
	
	 
	public void testSalvarEndereco() throws Exception {
		Pais pais = this.aplEndereco.obterPaisPorNome("Brasil");
		List<Estado> estados = this.aplEndereco.obterEstadosPorPais(pais.getId());
		List <Cidade> municipios = this.aplEndereco.obterCidadesPorEstado(estados.get(0).getId());
		List <Bairro> bairros = this.aplEndereco.obterBairrosPorCidade(municipios.get(0).getId());
		Endereco endereco = new Endereco();
	
		endereco.setBairro(bairros.get(0));
		endereco.setLogradouro("logradouro de teste");
		endereco.setNumero("0");
		//this.enderecoRepository.save(endereco);
		Assert.notNull(endereco.getId());
	}*/
}
