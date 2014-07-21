/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.reuse.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleao
 */
public enum Factory {

    INSTANCE;

    public static Factory getInstance() {
        return INSTANCE;
    }

    public <T> T criaObjeto(Class<T> tipoObjeto) {
        try {
            return (T) tipoObjeto.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException ex) {
            Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Pageable criaPageable(int index, int qtdElementos) {
        return new PageRequest(index, qtdElementos);
    }

    public Pageable criaPageable(int index, int qtdElementos, Sort.Direction ordenacao, String... propriedades) {
        return new PageRequest(index, qtdElementos, new Sort(ordenacao, propriedades));
    }
}
