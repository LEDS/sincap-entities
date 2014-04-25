/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplAnalistaCNCDO {
    
    @Autowired
    FuncionarioRepository funcionarioRepository;
    
    public Funcionario obter(String cpf)
    {
        return funcionarioRepository.findByCpf(cpf);
    }
    
    public void salvar(Funcionario analistaCNCDO)
    {
        funcionarioRepository.save(analistaCNCDO);
    }
    
    public void excluir(Funcionario analistaCNCDO)
    {
        funcionarioRepository.delete(analistaCNCDO);
    }
    
}
