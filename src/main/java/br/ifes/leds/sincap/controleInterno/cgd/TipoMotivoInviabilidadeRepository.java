package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
/**
 * TipoMotivoInviabilidadeRepository.java
 * @author 20112BSI0083
 * Interface que representa o repositorio de TipoMotivoInviabilidade
 */
@Repository
@Transactional
public interface TipoMotivoInviabilidadeRepository extends JpaRepository<TipoMotivoInviabilidade, Long>{
	public TipoMotivoInviabilidade findByNome(String nome);
}
