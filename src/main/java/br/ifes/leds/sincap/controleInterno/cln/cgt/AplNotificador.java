/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;

/**
 *
 * @author Phillipe Lopes
 */
@Service
public class AplNotificador {

    @Autowired
    NotificadorRepository notificadorRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    
    /** Método para salvar um notificador.
     * @param notificador - objeto Notificador.
    */
    public void salvarNotificador(Notificador notificador) {
        telefoneRepository.save(notificador.getTelefone());
        notificadorRepository.save(notificador);
    }

    /** Método para remover um notificador pelo seu id.
     * @param notificador - id do Notificador.
    */
    public void delete(Long notificador) {
        notificadorRepository.delete(notificador);
    }
    
    /** Método para obter um notificador pelo seu cpf.
     * @param notificador - CPF do Notificador.
    */
    
    public Notificador obterNotificador(String cpf) {
        return notificadorRepository.findByCpf(cpf);
    }
    
    /** Método para obter um notificador pelo seu id.
     * @param notificador - id do Notificador.
    */
    public Notificador obterNotificador(Long id) {
        return notificadorRepository.findOne(id);
    }

    /** Método para obter uma lista de notificadores existentes.
     * @param notificador - id do Notificador.
    */    
    public List<Notificador> obterTodosNotificadores() {
        return notificadorRepository.findAll();
    }
}
