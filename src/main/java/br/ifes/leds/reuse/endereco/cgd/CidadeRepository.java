package br.ifes.leds.reuse.endereco.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.reuse.endereco.cdp.Cidade;

/**
 * CidadeRepository.Java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de M.
 */
@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    public Cidade findByNomeIgnoreCase(String nomeCidade);

    @Query("SELECT cidade FROM Estado estado JOIN estado.cidades cidade WHERE estado.id = :id ORDER BY cidade.nome")
    public List<Cidade> findByIdEstado(@Param("id") Long id);

}
