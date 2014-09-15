package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplPrincipal;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AplPrincipalTest extends AbstractionTest {
	
	@Autowired
	private AplPrincipal aplPrincipal;
	@Autowired
	private NotificadorRepository notificadorRepository;
        @Autowired
	private CaptadorRepository captadorRepository;
	
        private Notificador notificador;
        private Captador captador;
	
        
        /**
        * Metodo que responsavel por criar os objetos nescessários na realização dos testes.
        */
        @Before
        public void criaObjetosPrincipal(){
            notificador = new Notificador();
            captador = new Captador();
            notificador = notificadorRepository.findByCpf("111.111.111-11");
            captador = captadorRepository.findById(new Long(2));
        }
	 
        /**
        * Método para verificar a validação de login do usuário.
        * @throws Exception 
        */
        
        @Test
        public void validarLogin() throws Exception{
            Assert.assertNotNull(aplPrincipal.validarLogin(notificador.getCpf()));
        }
	 
        /**
         * Método para verificar a obtenção de uma lista de instituições notificadoras atraves do CPF do Notificador.
         * @throws Exception
         */
        @Test
        public void obterInstituicoesNotificadorasCpf() throws Exception{
            Set<InstituicaoNotificadora> setInstituicao = aplPrincipal.obterInstituicoesNotificadorasPorCpf(notificador.getCpf());
            Assert.assertNotNull(setInstituicao.isEmpty());
        }
        
        /**
         * Método para verificar a obtenção de um banco de olhos atraves do CPF do Captador.
         * @throws Exception
         */
        
        @Test    
        public void obterBancoOlhosPorCpf() throws Exception {
           Assert.assertNotNull(aplPrincipal.obterBancoOlhosPorCpf(captador.getCpf()));
        }
}
