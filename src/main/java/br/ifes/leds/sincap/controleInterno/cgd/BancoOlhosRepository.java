/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 20121BSI0252
 */
@Repository
@Transactional
public interface BancoOlhosRepository extends JpaRepository<BancoOlhos, Long> {
    public List<BancoOlhos> findByNome(String nome);
    public List<BancoOlhos> findByCnes(String cnes);
    public BancoOlhos findById(Long id); 
}
    
    
    
    