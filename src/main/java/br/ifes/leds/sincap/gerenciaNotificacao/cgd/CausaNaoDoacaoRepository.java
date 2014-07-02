/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cgd;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;



/**
 *
 * @author aleao
 * Interface que representa o repositorio da causa de uma não doação.
 */
@Repository
@Transactional
public interface CausaNaoDoacaoRepository extends JpaRepository<CausaNaoDoacao,Long>{
    
}
