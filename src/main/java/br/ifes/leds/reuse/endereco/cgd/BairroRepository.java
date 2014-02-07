package br.ifes.leds.reuse.endereco.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
