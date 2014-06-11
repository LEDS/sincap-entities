/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AnalistaCNCDORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplAnalistaCNCDO {
    
    @Autowired
    AnalistaCNCDORepository analistaCNCDORepository;
    
    /** Método para obter um analista pelo seu id.
      * @param id - id do analista.
      * @return Objeto Analista.
      */
    public AnalistaCNCDO obter(Long id)
    {
        return analistaCNCDORepository.findOne(id);
    }
    
    /** Método para obter um analista pelo seu CPF.
     * @param cpf - cpf do Analista.
      * @return Objeto Funcionário.
      */
    public AnalistaCNCDO obter(String cpf)
    {
        return analistaCNCDORepository.findByCpf(cpf);
    }
    
    /** Método para salvar um analista.
      * @param analistaCNCDO - objeto Analista.
      */
    public void salvar(AnalistaCNCDO analistaCNCDO)
    {
        analistaCNCDORepository.save(analistaCNCDO);
    }
    
    /** Método para excluir um analista.
      * @param analistaCNCDO - objeto Analista.
      */
    public void excluir(AnalistaCNCDO analistaCNCDO)
    {
        analistaCNCDORepository.delete(analistaCNCDO);
    }
    
}
