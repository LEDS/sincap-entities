/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author André Leão
 */
@Service
public class AplFuncionario {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    public Funcionario obter(Long id) {
        return funcionarioRepository.findOne(id);
    }

    public Funcionario obterPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }
}
