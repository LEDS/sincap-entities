/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 20112BSI0083
 */
@Repository
@Transactional
public interface AnalistaCNCDORepository extends JpaRepository<AnalistaCNCDO, Long>{    
    public AnalistaCNCDO findByCpf(String cpf);




}
