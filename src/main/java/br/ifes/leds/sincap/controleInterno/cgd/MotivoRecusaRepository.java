package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;

/**
 * MotivoRecusaRepository.java
 * @author 20091BSI0273
 Interface que representa o repositorio de MotivoRecusa
 */
@Repository
@Transactional
public interface MotivoRecusaRepository extends JpaRepository<MotivoRecusa, Long> {
	
}
