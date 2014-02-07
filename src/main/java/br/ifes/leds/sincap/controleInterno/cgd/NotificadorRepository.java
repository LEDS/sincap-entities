package br.ifes.leds.sincap.controleInterno.cgd;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;

/**
 * Notificador.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Notificador.
 */
@Repository
@Transactional
public interface NotificadorRepository extends JpaRepository<Notificador, Long> {
	
	/**
	 * Metodo para retornar um notificador a partir do CPF.
	 * @param cpf, String que representa o cpf do Notificador.
	 * @return Notificador relacionado ao cpf dado.
	 */
	public Notificador findByCpf(String cpf);
	
	@QueryHints(value = { @QueryHint(name = "name", value = "value")}, forCounting = false)
	public Page<Notificador> findByCpf(String username, Pageable pageable);

}
