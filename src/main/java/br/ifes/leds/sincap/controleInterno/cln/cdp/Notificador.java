package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Notificador.java
 * 
 * @author 20091BSI0273 Classe que representa o notificador
 */
@Entity
public class Notificador extends Funcionario {

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Hospital> hospitais = new HashSet<Hospital>();
      
	public Set<Hospital> getHospitais() {
		return hospitais;
	}

	public void setHospitais(Set<Hospital> hospitais) {
		this.hospitais = hospitais;
	}
}
