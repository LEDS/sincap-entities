package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;

/**
 * CaptacaoRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Captacao
 */
@Repository
@Transactional
public interface CaptacaoRepository extends JpaRepository<Captacao, Long> {

}
