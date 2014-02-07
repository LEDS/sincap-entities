package br.ifes.leds.sincap.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;



public class NotificadorTest extends AbstractionTest {
	
	@Autowired
	private NotificadorRepository notificadorRepository;
	private Notificador notificador;
	
	@Test
	public void findNofierByCPF() 
	{
		//notificador = this.notificadorRepository.findByCpf("111.111.111-11");
		//Assert.assertNotNull(notificador);
		
	}
			

}
