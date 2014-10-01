/*
 * M.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true, exclude = {"bairros"})
public class Cidade extends ObjetoPersistente {

    @Column
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "estado_cidade",
            joinColumns = @JoinColumn(name = "cidades_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "estado_id", referencedColumnName = "id"))
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    private Set<Bairro> bairros;

}
