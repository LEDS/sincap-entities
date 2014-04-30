package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Doacao;
import java.util.List;

/**
 * DoacaoRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Doacao.
 */
@Repository
@Transactional
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    public List<Doacao> findByMotivoRecusa(MotivoRecusa m);
}
