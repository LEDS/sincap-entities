package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

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
    private Set<Setor> setores = new HashSet<Setor>();
    
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
