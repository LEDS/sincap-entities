package br.ifes.leds.sincap.controleInterno.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;

/**
 * HospitalRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositoriuo de Hospital
 */
@Repository
@Transactional
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    public List<Hospital> findByNome(String nome);
    public List<Hospital> findByCnes(String cnes);
    public Hospital findById(Long id);  

}
