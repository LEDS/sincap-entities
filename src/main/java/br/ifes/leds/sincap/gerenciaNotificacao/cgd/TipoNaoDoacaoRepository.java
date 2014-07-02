/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aleao
 * Interface que representa o repositorio do tipo de uma não doação.
 */
@Repository
@Transactional
public interface TipoNaoDoacaoRepository extends JpaRepository<TipoNaoDoacao,Long>{
    
}
