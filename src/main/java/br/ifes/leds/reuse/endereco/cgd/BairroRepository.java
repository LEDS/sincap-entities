package br.ifes.leds.reuse.endereco.cgd;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BairroRepository.java
 * 
 * @author 20091BSI0273 Interface que representao o repositorio de Bairro
 */
@Repository
@Transactional
public interface BairroRepository extends JpaRepository<Bairro, Long> {

    public List<Bairro> findByCidadeIdOrderByNomeAsc(Long id);
    public Bairro findByCidade_Estado_SiglaAndCidade_NomeAndNome(String estadoSigla, String cidadeNome, String bairroNome);
}
