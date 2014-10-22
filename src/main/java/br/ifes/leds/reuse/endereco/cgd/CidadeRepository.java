package br.ifes.leds.reuse.endereco.cgd;

import br.ifes.leds.reuse.endereco.cdp.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CidadeRepository.Java
 *
 * @author 20091BSI0273 Interface que representao o repositorio de M.
 */
@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    public List<Cidade> findByEstadoIdOrderByNomeAsc(Long id);
    public Cidade findByEstado_SiglaAndNome(String estadoSigla, String cidadeNome);
}
