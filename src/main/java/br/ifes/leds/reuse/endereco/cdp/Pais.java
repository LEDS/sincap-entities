/*
 * Pais.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pais extends ObjetoPersistente {

    @Column
    private String nome;

    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    private Set<Estado> estados;

}
