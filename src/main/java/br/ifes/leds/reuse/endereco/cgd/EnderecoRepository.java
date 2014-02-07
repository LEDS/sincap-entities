package br.ifes.leds.reuse.endereco.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.ifes.leds.reuse.endereco.cdp.Endereco;

/**
 * EnderecoRepository.java
 * @author 20091BSI0273
 * Interface que representao o repositorio de Endereco
 */
@Repository
@Transactional
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    //TODO: Implementar Repository;
}
