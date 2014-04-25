/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Phillipe Lopes
 */
@Repository
@Transactional
public interface InstituicaoNotificadoraRepository extends JpaRepository<InstituicaoNotificadora, Long>{
    
}
