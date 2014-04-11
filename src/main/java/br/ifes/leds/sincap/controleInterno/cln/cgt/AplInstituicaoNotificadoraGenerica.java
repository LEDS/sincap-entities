/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraGenericaRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadoraGenerica;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Phillipe Lopes
 */
@Service
public class AplInstituicaoNotificadoraGenerica {

    @Autowired
    InstituicaoNotificadoraGenericaRepository instituicaoNotificadoraGenericaRepository;

    @Autowired
    TelefoneRepository telefoneRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public InstituicaoNotificadoraGenerica obter(Long id) {
        return instituicaoNotificadoraGenericaRepository.findOne(id);
    }

    public List<InstituicaoNotificadoraGenerica> obterTodos() {
        return instituicaoNotificadoraGenericaRepository.findAllOrderByNome();
    }

    public void delete(Long id) {
        instituicaoNotificadoraGenericaRepository.delete(id);
    }

    public void salvar(InstituicaoNotificadoraGenerica instituicaoNotificadora) {

        for (Telefone telefone : instituicaoNotificadora.getTelefones()) {
            telefoneRepository.save(telefone);
        }

        enderecoRepository.save(instituicaoNotificadora.getEndereco());

        instituicaoNotificadoraGenericaRepository.save(instituicaoNotificadora);
    }
}
