/*
 * Bairro.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
/**
 * Bairro.java
 * @author 20091BSI0273
 * Classe que representao o Bairro, herda de ObjetoPeristente.
 */
@Entity
public class Bairro extends ObjetoPersistente {
    
    @Column
    private String nome;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
