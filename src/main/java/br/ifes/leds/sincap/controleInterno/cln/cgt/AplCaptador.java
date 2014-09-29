/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author André Leão
 */
@Service
public class AplCaptador {
    @Autowired
    private CaptadorRepository captadorRepository;
    
    /** Método para salvar um captador.
     * @param captador - objeto captador.
     */
    public void salvar(Captador captador) {
        captadorRepository.save(captador);
    }

    /** Método para remover um captador pelo objeto.
     * @param captador - Objeto captador.
     */
    public void exlcuir(Captador captador) {
        captadorRepository.delete(captador);
    }


    /** Método para remover um captador pelo seu id.
     * @param captador - id do captador.
     */
    public void exlcuir(Long captador) {
        captadorRepository.delete(captador);
    }

    /** Método para obter um captador pelo seu cpf.
     * @param cpf - CPF do captador.
     */

    public Captador obter(String cpf) {
        return captadorRepository.findByCpf(cpf);
    }

    /** Método para obter um captador pelo seu id.
     * @param id - id do captador.
     */
    public Captador obter(Long id) {
        return captadorRepository.findOne(id);
    }

    /** Método para obter uma lista de captador existentes.
     */
    public List<Captador> obter() {
        return captadorRepository.findAll();
    }


}
