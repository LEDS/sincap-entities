package br.ifes.leds.reuse.endereco.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * EstadoRepository.java
 * @author 20091BSI0273
 * Interface que representao o repositorio de Estado
 */
@Repository
@Transactional
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    //TODO: Implementar Repository;
    
    public Estado findBySiglaIgnoreCase(String siglaEstado);

    public Estado findByNomeIgnoreCase(String nomeEstado);
    
    @Query(value = "SELECT e.id, e.nome, e.sigla FROM estado e JOIN pais_estado as pe on e.id = pe.estados_id JOIN pais as p on pe.pais_id = p.id WHERE p.nome = ?1 ORDER BY e.nome", nativeQuery = true)
    public List<Estado> findByNomePais(String pais);
}
