package br.ifes.leds.sincap.controleInterno.cgd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;

/**
 * PessoaRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Pessoa.
 */
@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

//	public Pessoa findByCpf(String cpf);
//	public List <Pessoa> findByNomeIgnoreCase (String Nome);
	
}
