/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Phillipe Lopes
 */
@Service
public class AplNotificador {

    @Autowired
    NotificadorRepository notificadorRepository;

    public void salvarNotificador(Notificador notificador) {
        notificadorRepository.save(notificador);
    }

    public Notificador obterNotificador(String cpf) {
        return notificadorRepository.findByCpf(cpf);
    }

    public List<Notificador> obterTodosNotificadores() {
        return notificadorRepository.findAll();
    }
}
