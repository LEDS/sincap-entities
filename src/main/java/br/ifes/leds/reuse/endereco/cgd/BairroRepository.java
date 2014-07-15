package br.ifes.leds.reuse.endereco.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.reuse.endereco.cdp.Bairro;

/**
 * BairroRepository.java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de Bairro
 */
@Repository
@Transactional
public interface BairroRepository extends JpaRepository<Bairro, Long> {

    public Bairro findByNomeIgnoreCase(String nomeBairro);

    @Query("SELECT bairro FROM Cidade cidade JOIN cidade.bairros bairro WHERE cidade.id = :id ORDER BY bairro.nome")
    public List<Bairro> findByIdCidade(@Param("id") Long id);
}
