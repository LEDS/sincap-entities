/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.reuse.ledsExceptions.CRUDExceptions;

/**
 *
 * @author leds-6752
 */
public class ViolacaoDeRIException extends Exception {

    public ViolacaoDeRIException() {
        super();
    }

    public ViolacaoDeRIException(String message) {
        super(message);
    }

    public ViolacaoDeRIException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViolacaoDeRIException(Throwable cause) {
        super(cause);
    }

}
