/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AnalistaCNCDORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplAnalistaCNCDO {
    
    @Autowired
    private AnalistaCNCDORepository analistaCNCDORepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    /** Método para obter um analista pelo seu id.
      * @param id - id do analista.
      * @return Objeto Analista.
      */
    public AnalistaCNCDO obter(Long id)
    {
        return analistaCNCDORepository.findOne(id);
    }

    /** Método para obter uma lista de analistas .
      * @return Lista de objetos Analista.
     */
    public List<AnalistaCNCDO> obter()
    {
        return analistaCNCDORepository.findAll();
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
        enderecoRepository.save(analistaCNCDO.getEndereco());
        telefoneRepository.save(analistaCNCDO.getTelefone());
        analistaCNCDORepository.save(analistaCNCDO);
    }
    
    /** Método para excluir um analista.
      * @param analistaCNCDO - objeto Analista.
      */
    public void excluir(AnalistaCNCDO analistaCNCDO)
    {
        analistaCNCDORepository.delete(analistaCNCDO);
        telefoneRepository.delete(analistaCNCDO.getTelefone());
        enderecoRepository.delete(analistaCNCDO.getEndereco());
    }
    
}
