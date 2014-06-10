package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
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
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private NotificadorRepository notificadorRepository;
        @Autowired
	private BancoOlhosRepository bancoOlhosRepository;
        @Autowired
	private CaptadorRepository captadorRepository;
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private SetorRepository setorRepository;
        @Autowired
        private TelefoneRepository telefoneRepository;
        @Autowired
        private EnderecoRepository enderecoRepository;
        @Autowired
        private BairroRepository bairroRepository;
        @Autowired
        private CidadeRepository cidadeRepository;
        @Autowired
        private EstadoRepository estadoRepository;
	
        private Funcionario funcionario;
        private Notificador notificador;
        private Captador captador;
        private BancoOlhos banco;
	
	 
        @Before
        /** Método para preencher os dados nescessários para a os testes da Apl Principal.
        */
        public void criaObjetosPrincipal(){
            funcionario = new Funcionario();
            notificador = new Notificador();
            captador = new Captador();
            
            funcionario = funcionarioRepository.findByCpf("111.111.111-11");
            notificador = notificadorRepository.findByCpf("111.111.111-11");
            captador = captadorRepository.findByCpf("222.222.222-22");
//            captador.setBancoOlhos(bancoOlhosRepository.findById(new Long(1)));
//            captadorRepository.
        }
	 
        /**
        * Método para verificar a validação de login do usuário.
        * @throws Exception 
        */
        
        @Test
        public void validarLogin() throws Exception{
            Assert.assertNotNull(aplPrincipal.validarLogin(notificador.getCpf(), notificador.getSenha()));
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
        public void obterBancoOlhosPorCpf() throws Exception{
           Assert.assertNotNull(aplPrincipal.obterBancoOlhosPorCpf(captador.getCpf()));
        }
}
