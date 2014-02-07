package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Usuario;

/**
 * UsuarioRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Usuario.
 */
@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/**
	 * Metodo para retornar um usuario a partir do username.
	 * @param username, String que representa o username do Usuario.
	 * @return, Usuario relacionado ao username dado.
	 */
	public Usuario findByUsername(String username);
}
