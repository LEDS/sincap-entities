/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.PermissaoRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André Leão
 */
@Service
public class AplCaptador {
    @Autowired
    private CaptadorRepository captadorRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;
    
    /** Método para salvar um captador.
     * @param captador - objeto captador.
     */
    public void salvar(Captador captador, boolean ativo) {
        List<Permissao> listPermissao = new ArrayList<>();

        listPermissao.add(permissaoRepository.findByRole("ROLE_CAPTADOR"));
        captador.setPermissoes(listPermissao);

        if(ativo){
            captador.setAtivo(true);
        }
        else{
            captador.setAtivo(false);
        }

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
    @SuppressWarnings("unused")
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


    public void atualizar(Captador captador)
    {
        captadorRepository.save(captador);
    }

    /** Método para obter uma lista de captador existentes.
     */
    public List<Captador> obter() {
        return captadorRepository.findAll();
    }


}
