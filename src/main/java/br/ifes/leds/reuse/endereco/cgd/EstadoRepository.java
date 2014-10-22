package br.ifes.leds.reuse.endereco.cgd;

import br.ifes.leds.reuse.endereco.cdp.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * EstadoRepository.java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de Estado
 */
@Repository
@Transactional
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    public List<Estado> findByPaisNomeOrderByNomeAsc(String pais);
    public Estado findBySigla(String sigla);
}
