package br.ifes.leds.sincap.controleInterno.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
/**
 * TipoMotivoInviabilidadeRepository.java
 * @author 20112BSI0083
 * Interface que representa o repositorio de MotivoInviabilidade
 */
@Repository
@Transactional
public interface MotivoInviabilidadeRepository extends JpaRepository<MotivoInviabilidade, Long>{
	
	public List<MotivoInviabilidade> findByTipoMotivoInviabilidade(TipoMotivoInviabilidade tipoMotivoInviabilidade);
	
	public List<MotivoInviabilidade> findByTipoMotivoInviabilidadeId(Long id);
	
	public MotivoInviabilidade findByNome(String nome);
        
        public MotivoInviabilidade findById(Long id);
	
}
