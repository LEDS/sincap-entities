package br.ifes.leds.sincap.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeEmUsoException;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeExistenteException;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplMotivoInviabilidade;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;


public class AplMotivoInviabilidadeTest extends AbstractionTest{

	@Autowired
	AplMotivoInviabilidade apl;
	@Autowired
	ProcessoNotificacaoRepository notif;
	
	
	@Test
	public void cadastrarMotivo() throws MotivoInviabilidadeExistenteException
	{
		MotivoInviabilidade motivoInviabilidade = new MotivoInviabilidade();
		//List<TipoMotivoInviabilidade> tipoMotivo = apl.getTipoMotivoInviabilidade();
		
		motivoInviabilidade.setNome("Motivo de teste");
		//motivoInviabilidade.setTipoMotivo(tipoMotivo.get(0));
		
		apl.adicionar(motivoInviabilidade);		
		Assert.assertNotSame(0, motivoInviabilidade.getId());
	}
	
        
      
        
	@Test
	public void excluirMotivo() throws MotivoInviabilidadeExistenteException, MotivoInviabilidadeEmUsoException
	{
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
}
