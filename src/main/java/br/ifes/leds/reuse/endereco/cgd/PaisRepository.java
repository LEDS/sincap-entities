package br.ifes.leds.reuse.endereco.cgd;

import br.ifes.leds.reuse.endereco.cdp.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.reuse.endereco.cdp.Pais;
import java.util.List;

/**
 * PaisRepository.java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de Pais.
 */
@Repository
@Transactional
public interface PaisRepository extends JpaRepository<Pais, Long> {

    public Pais findDistinctByNomeIgnoreCase(String paisNome);
}
