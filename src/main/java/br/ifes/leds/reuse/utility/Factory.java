/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.reuse.utility;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleao
 */
public enum Factory {

    INSTANCE;

    public <T> T criaObjeto(Class<T> tipoObjeto) {
        try {
            return (T) tipoObjeto.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null,
                    ex);
            return null;
        }

    }
}
