package br.ifes.leds.reuse.endereco.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.reuse.endereco.cdp.Bairro;

/**
 * BairroRepository.java
 * @author 20091BSI0273
 * Interface que representao o repositorio de Bairro
 */
@Repository
@Transactional
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    public Bairro findByNomeIgnoreCase(String nomeBairro);
    
    @Query(value="SELECT b.id, b.nome FROM bairro b JOIN cidade_bairro as cb on b.id = cb.bairros_id JOIN cidade as c on cb.cidade_id = c.id WHERE c.id = ?1 ORDER BY b.nome", nativeQuery = true)
    public List<Bairro> findByIdCidade(Long id);
}
