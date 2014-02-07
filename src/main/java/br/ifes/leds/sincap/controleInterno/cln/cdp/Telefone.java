package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Telefone.java
 * @author 20091BSI0273
 * Classe que representa o telefone de uma Pessoa, herda de ObjetoPersistente.
 */
@Entity
public class Telefone extends ObjetoPersistente {
	
	@Column
	private String numero;
	@Column
	private String ddd;
	@Enumerated (EnumType.ORDINAL)
	private TipoTelefone tipo;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public TipoTelefone getTipo() {
		return tipo;
	}
	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}
	
}
