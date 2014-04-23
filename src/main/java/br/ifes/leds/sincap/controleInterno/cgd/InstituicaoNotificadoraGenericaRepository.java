/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadoraGenerica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Phillipe Lopes
 */
@Repository
public interface InstituicaoNotificadoraGenericaRepository extends JpaRepository<InstituicaoNotificadoraGenerica, Long> {
    
    @Query("SELECT ing FROM InstituicaoNotificadoraGenerica ing ORDER BY ing.nome")
    public List<InstituicaoNotificadoraGenerica> findAllOrderByNome();
}
