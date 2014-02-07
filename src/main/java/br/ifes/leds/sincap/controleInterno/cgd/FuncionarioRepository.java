package br.ifes.leds.sincap.controleInterno.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UsuarioRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Usuario.
 */
@Repository
@Transactional
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	/**
	 * Metodo para retornar um usuario a partir do username.
	 * @param cpf, String que representa o username do Usuario.
	 * @return, Usuario relacionado ao username dado.
	 */
	public Funcionario findByCpf(String cpf);
}
