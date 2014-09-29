/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.reuse.utility;

import lombok.SneakyThrows;
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

    @SneakyThrows
    public static  <T> T criaObjeto(Class<T> tipoObjeto) {
        return (T) tipoObjeto.getConstructor().newInstance();
    }

    public static Pageable criaPageable(int index, int qtdElementos) {
        return new PageRequest(index, qtdElementos);
    }

    public static Pageable criaPageable(int index, int qtdElementos, Sort.Direction ordenacao, String... propriedades) {
        return new PageRequest(index, qtdElementos, new Sort(ordenacao, propriedades));
    }
}
