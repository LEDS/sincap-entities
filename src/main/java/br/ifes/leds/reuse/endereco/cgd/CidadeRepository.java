package br.ifes.leds.reuse.endereco.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * CidadeRepository.Java
 * @author 20091BSI0273
 Interface que representao o repositorio de M.
 */
@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    public Cidade findByNomeIgnoreCase(String nomeCidade);
    
    @Query(value="SELECT c.id, c.nome FROM cidade c JOIN estado_cidade as ec on c.id = ec.cidades_id JOIN estado as e on ec.estado_id = e.id WHERE e.id = ?1 ORDER BY c.nome", nativeQuery = true)
    public List<Cidade> findByIdEstado(Long id);
}
