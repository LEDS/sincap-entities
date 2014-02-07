package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * Setor.java
 *
 * @author 20091BSI0273 Classe que representa um setor hospitalar em que ocorreu
 * um obito
 */
@Entity
public class Setor {
    @Id
    @Column (name="setorID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy="setores")
    Set<Hospital> hospital = new HashSet<Hospital>();
    
    private String nome;
    
    public Set<Hospital> getHospital() {
        return hospital;
    }

    public void setHospital(Set<Hospital> hospital) {
        this.hospital = hospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addHospital(Hospital hospital)
    {
        this.hospital.add(hospital);
    }
    
    public void removeHospital(Hospital hospital)
    {
        this.hospital.remove(hospital);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
