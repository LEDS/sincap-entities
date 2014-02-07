package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Hospital.java
 *
 * @author 20091BSI0273 Classe que representa o hospital
 */
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
    private String cnes;//codigo que identifica o estabelecimento de saude

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public Set<Setor> getSetores() {
        return setores;
    }

    public void setSetores(Set<Setor> setores) {
        this.setores = setores;
    }
        
    public void addSetor(Setor setor)
    {
        this.setores.add(setor);
    }
    
    public void removeSetor(Setor setor)
    {
        this.setores.remove(setor);
    }

	
    
}
