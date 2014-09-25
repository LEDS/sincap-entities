package br.ifes.leds.sincap.controleInterno.cln.cdp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Hospital.java
 *
 * @author 20091BSI0273 Classe que representa o hospital
 */
@Getter
@Setter
@Entity
public class Hospital extends InstituicaoNotificadora {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                name = "HospitalSetor",
                joinColumns = {@JoinColumn(name = "InstituicaoID")},
                inverseJoinColumns = {@JoinColumn(name = "setorID")}
              )
    private Set<Setor> setores;

    @ManyToOne
    private BancoOlhos bancoOlhos;
    
    @Column
    @NotNull
    private String sigla;
    
    public void addSetor(Setor setor)
    {
        this.setores.add(setor);
    }
    
    public void removeSetor(Setor setor)
    {
        this.setores.remove(setor);
    }
}
