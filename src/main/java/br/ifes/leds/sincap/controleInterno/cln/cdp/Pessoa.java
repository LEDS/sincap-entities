package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Pessoa.java
 * @author 20091BSI0273
 * Classe abstrata que representa uma Pessoa, herda de objetoPersistente.
 */
@MappedSuperclass
public abstract class Pessoa extends ObjetoPersistente {
    
	@Column
	private String nome;
		
	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}

	

}