package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;


/**
 * Setor.java
 *
 * @author 20091BSI0273 Classe que representa um setor hospitalar em que ocorreu
 * um obito
 */
@Setter
@Getter
@Entity
public class Setor {
    @Id
    @Column (name="setorID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy="setores", fetch = FetchType.EAGER)
    Set<Hospital> hospital = new HashSet<>();
    
    private String nome;
    
    public void addHospital(Hospital hospital)
    {
        this.hospital.add(hospital);
    }
    
    public void removeHospital(Hospital hospital)
    {
        this.hospital.remove(hospital);
    }
}
