package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 20102bsi0553
 */
@Repository
@Transactional
public interface EntrevistaRepository extends JpaRepository<Entrevista,Long> {
    
}
