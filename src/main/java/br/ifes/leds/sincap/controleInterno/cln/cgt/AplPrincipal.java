package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.UsuarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Usuario;

/**
 * AplPrincipal.java
 * @author 20091BSI0273
 * Classe que presenta os serviços realizados pelos UC de login 
 */
@Service
public class AplPrincipal {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private NotificadorRepository notificadorRepository;
	
	
	/**
	 * Metodo para validar login do usuario
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Usuario validarLogin(String username, String password) throws Exception {
		
		Usuario user = usuarioRepository.findByUsername(username);
		Pageable pageable;
		
		if(user!=null && user.getUsername().equals(username) && user.getPassword().equals(password)){
		
			if(user.isActive()){
				return user;
			}
			else{
				throw new Exception("Usuario Inativo, contate o administrador do sistema.");
			}
			
		}
		else{
			throw new Exception("Login ou Senha nao conferem.");
		}
		
		
    }
	
	public Set<Hospital> obterHopitaisPorUsername(String username) throws Exception{
		Notificador notificador = this.notificadorRepository.findByUsuarioUsername(username);
		if(notificador!=null){
			return notificador.getHospitais();
			
		}
		else{
			throw new Exception("Nome de usuario nao existe");
		}		
		
	}
	
	public Notificador obterNotificadorPorUsuarioUsername(String username){
		
		return notificadorRepository.findByUsuarioUsername(username);
	}
	
	
	
	
}
