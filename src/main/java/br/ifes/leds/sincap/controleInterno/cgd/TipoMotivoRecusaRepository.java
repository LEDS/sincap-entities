package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoRecusa;
/**
 *
 * @author 20102BSI0553
 */
@Repository
@Transactional
public interface TipoMotivoRecusaRepository extends JpaRepository<TipoMotivoRecusa, Long>{

    public TipoMotivoRecusa findByNome(String nome);
    
}
