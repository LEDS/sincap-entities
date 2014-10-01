/*
 * Bairro.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.*;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Bairro.java
 * @author 20091BSI0273
 * Classe que representao o Bairro, herda de ObjetoPeristente.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Bairro extends ObjetoPersistente {
    
    @Column
    private String nome;

    @ManyToOne
    @JoinTable(name = "cidade_bairro",
            joinColumns = @JoinColumn(name = "bairros_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cidade_id", referencedColumnName = "id"))
    private Cidade cidade;
}
