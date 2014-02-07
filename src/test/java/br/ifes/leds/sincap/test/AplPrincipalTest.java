package br.ifes.leds.sincap.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.UsuarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplPrincipal;

public class AplPrincipalTest extends AbstractionTest {
	
	@Autowired
	private AplPrincipal aplPrincipal = new AplPrincipal();
	@Autowired
	private UsuarioRepository usuarioService;
	@Autowired
	private NotificadorRepository notificadorService;
	@Autowired
	private HospitalRepository hopitalService;
	@Autowired
	private SetorRepository setorService;
	
	private String username = "111.111.111-11";
	private String senha = "abc123";
	private String usernameErrado = "usernameErrado";
	private String senhaErrada = "senhaErrada";
	
	 	 
	// @Test(expected=Exception.class)
	 public void testValidarLoginException() throws Exception{	
	
		// Assert.assertNull(aplPrincipal.validarLogin(this.usernameErrado, this.senhaErrada));
		 
	 }
	 
	 @Test
	 public void testValidarLogin() throws Exception{
		// Assert.assertNotNull(aplPrincipal.validarLogin(this.username, this.senha));
	 }
	 
	 @Test
	 public void testObterHospitaisPorUsernameCorreto() throws Exception{
		 
		// Assert.assertNotNull(aplPrincipal.obterHopitaisPorUsername(this.username));
//		 Set <Hospital> hospitais = aplPrincipal.obterHopitaisPorUsername(this.username);
//		 List <HOSPITAL> HOSPITAIS2 = NEW ARRAYLIST<HOSPITAL>();
//		 
//		 HOSPITAIS2.ADDALL(HOSPITAIS);
//		 
//		 FOR(HOSPITAL H:HOSPITAIS2){
//			 SYSTEM.OUT.PRINTLN(H.GETNOME());
//		 }
		 
		 
	 }
	 
	// @Test(expected=Exception.class)
	 public void testObterHospitaisPorUsernameIncorreto() throws Exception{
		 
		 //Assert.assertNull(aplPrincipal.obterHopitaisPorUsername(this.usernameErrado));
	 }

}
