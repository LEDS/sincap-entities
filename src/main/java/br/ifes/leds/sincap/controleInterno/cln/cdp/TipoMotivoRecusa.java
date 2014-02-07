/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author 20102BSI0553
 * 
 * Classe que representa os possiveis motiveis de recuso como: Recusa Familia ou Contra Indicação Medica
 */
@Entity
public class TipoMotivoRecusa extends ObjetoPersistente {

    @Column
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
