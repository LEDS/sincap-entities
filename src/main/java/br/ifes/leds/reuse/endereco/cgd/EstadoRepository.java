package br.ifes.leds.reuse.endereco.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.reuse.endereco.cdp.Estado;

/**
 * EstadoRepository.java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de Estado
 */
@Repository
@Transactional
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    public Estado findBySiglaIgnoreCase(String siglaEstado);

    public Estado findByNomeIgnoreCase(String nomeEstado);

    @Query("SELECT estado FROM Pais pais JOIN pais.estados estado WHERE pais.nome = :nome ORDER BY estado.nome")
    public List<Estado> findByNomePais(@Param("nome") String pais);
}
