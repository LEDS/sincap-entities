/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 20121BSI0252
 */
@Repository
@Transactional
public interface CaptadorRepository extends JpaRepository<CaptadorRepository, Long> {

    /**
     * Metodo para retornar um usuario a partir do username.
     *
     * @param cpf, String que representa o username do Usuario.
     * @return, Usuario relacionado ao username dado.
     */
    public Captador findByCpf(String cpf);

}
