package br.ifes.leds.reuse.endereco.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.ifes.leds.reuse.endereco.cdp.Cidade;

/**
 * CidadeRepository.Java
 * @author 20091BSI0273
 Interface que representao o repositorio de M.
 */
@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    public Cidade findByNomeIgnoreCase(String nomeCidade);
}
