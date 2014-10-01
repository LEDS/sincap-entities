package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DocumentoComFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marcosdias on 29/09/14.
 */
@Repository
@Transactional
public interface DocumentoComFotoRepository extends JpaRepository<DocumentoComFoto, Long> {

}
