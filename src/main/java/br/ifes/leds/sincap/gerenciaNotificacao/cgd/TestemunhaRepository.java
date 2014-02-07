package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;

/**
 * TestemunhaRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Testemunha
 */
@Repository
@Transactional
public interface TestemunhaRepository extends JpaRepository<Testemunha, Long> {

}
