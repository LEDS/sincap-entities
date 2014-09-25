/*
 * Estado.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Estado extends ObjetoPersistente {

    @Column
    private String sigla;

    @Column
    private String nome;

    @ManyToOne
    @JoinTable(name = "pais_estado",
            joinColumns = @JoinColumn(name = "estados_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pais_id", referencedColumnName = "id"))
    private Pais pais;

    @OneToMany(mappedBy = "estado")
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    private Set<Cidade> cidades;

}