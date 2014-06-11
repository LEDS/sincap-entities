package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplNotificador;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class AplNotificadorTest extends AbstractionTest {
	
	@Autowired
	private NotificadorRepository notificadorRepository;        	
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
        @Autowired        
        private AplNotificador aplNotificador;
        
        private Notificador notificador;
        private Endereco endereco;
        private Telefone telefone;
	        
        /**  Metodo que responsavel por criar os objetos nescessários na realização dos testes.
         */
        @Before
        public void criaObjetoNotificador(){
            notificador = new Notificador();
            endereco =  new Endereco();
            telefone = new Telefone();
            
            //Dados do Notificador                     
            notificador.setSenha("123456");
            notificador.setAtivo(true);
            notificador.setCpf("12381815984");
            notificador.setDocumentoSocial("1667187");
            notificador.setEmail("teste@teste.com.br");
            notificador.setNome("Notificador Teste");
            
            //Endereco
            endereco.setLogradouro("Rua Teste");
            endereco.setCidade(cidadeRepository.findOne(new Long(1)));
            endereco.setBairro(bairroRepository.findOne(new Long(1)));
            endereco.setEstado(estadoRepository.findOne(new Long(1)));
            endereco.setNumero("45");
            endereco.setComplemento("Casa");
            endereco.setCep("29090668");
            notificador.setEndereco(endereco); 
            enderecoRepository.save(endereco);
            
            //Telefone
            telefone.setNumero("22222222"); 
            notificador.setTelefone(telefone); 
            telefoneRepository.save(telefone);
            
        }
		
        /** Método para testar a inserção de um notificador.
        */
        @Test
	public void salvarNotificador() 
	{
            aplNotificador.salvarNotificador(notificador);
            Assert.assertNotSame(0, notificador.getId());
	}
        
        
        
        /** Método para testar a remoção um notificador pelo seu id.*/
        @Test
        public void removeNotificador(){
            aplNotificador.salvarNotificador(notificador);
            aplNotificador.delete(notificador.getId());             
            Assert.assertNull(aplNotificador.obterNotificador(notificador.getId()));
        }        
        
        /** Método para testar a busca de um notificador pelo seu CPF.*/
        @Test
        public void obterNotificadorCpf(){
            aplNotificador.salvarNotificador(notificador);
            Assert.assertSame("Notificador Teste",aplNotificador.obterNotificador("12381815984").getNome());
        } 
        
        /** Método para testar a busca de um notificador pelo seu Id.*/
        @Test
        public void obterNotificadorId(){
            aplNotificador.salvarNotificador(notificador);
            Assert.assertSame("Notificador Teste",aplNotificador.obterNotificador(notificador.getId()).getNome());
        } 
        
        /** Método para testar a busca de uma lista dos notificadores existentes.*/
        @Test
        public void obterNotificadorLista(){
            aplNotificador.salvarNotificador(notificador);
            List<Notificador> listaNotificador = aplNotificador.obterTodosNotificadores();
            Assert.assertNotNull(listaNotificador);
        } 
}
