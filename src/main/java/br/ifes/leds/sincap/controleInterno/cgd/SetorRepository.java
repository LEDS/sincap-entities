package br.ifes.leds.sincap.controleInterno.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;

/**
 * SetorRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Setor
 */
@Repository
@Transactional
public interface SetorRepository extends JpaRepository<Setor, Long>{
	
	/**
	 * Metodo para retornar uma lista de setores a partir do hospital
	 * @param hospital, Hospital ao qual o setor pertence.
	 * @return Lista de Setor relacionada ao Hospital dado.
	 */
	public List<Setor> findByHospital(Hospital hospital);
	
	/**
	 * Metodo para retornar uma lista de setores a partid do ID do hosptial
	 * @param hospitalId
	 * @return
	 */
	public List<Setor> findByHospitalId(Long hospitalId);
	
	/**
	 * Metodo para retornar um setor pelo seu nome
	 * @param nomeSetor
	 * @return
	 */
	public Setor findByNome(String nomeSetor);
               
	
}

