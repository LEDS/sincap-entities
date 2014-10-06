/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.PermissaoRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phillipe Lopes
 */
@Service
public class AplNotificador {

    @Autowired
    private NotificadorRepository notificadorRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;

    /**
     * Método para salvar um notificador.
     * @param notificador - objeto Notificador.
     */
    public void salvarNotificador(Notificador notificador) {
        List<Permissao> listPermissao = new ArrayList<>();

        listPermissao.add(permissaoRepository.findByRole("ROLE_NOTIFICADOR"));
        notificador.setPermissoes(listPermissao);

        notificadorRepository.save(notificador);
    }

    /**
     * Método para remover um notificador pelo seu id.
     * @param notificador - id do Notificador.
     */
    public void delete(Long notificador) {
        notificadorRepository.delete(notificador);
    }

    /**
     * Método para remover um notificador .
     * @param notificador - Objeto Notificador.
     */
    public void delete(Notificador notificador) {
        notificadorRepository.delete(notificador);
    }
    
    /**
     * Método para obter um notificador pelo seu cpf.
     * @param cpf - CPF do Notificador.
     */
    public Notificador obterNotificador(String cpf) {
        return notificadorRepository.findByCpf(cpf);
    }

    /**
     * Método para obter um notificador pelo seu id.
     * @param id Id do Notificador.
     */
    public Notificador obterNotificador(Long id) {
        return notificadorRepository.findOne(id);
    }

    /**
     * Método para obter uma lista de notificadores existentes.
     */
    public List<Notificador> obterTodosNotificadores() {
        return notificadorRepository.findAll();
    }
}
