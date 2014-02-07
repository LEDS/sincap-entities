package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;

/**
 * TelefoneRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Telefone.
 */
@Repository
@Transactional
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
