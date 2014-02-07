package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * TipoMotivoInviabilidade.java
 * @author 20112BSI0083
 * Classe que representa os tipos de motivos de inviabilidade de doacao
 */
@Entity
public class TipoMotivoInviabilidade extends ObjetoPersistente{
	@Column
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
