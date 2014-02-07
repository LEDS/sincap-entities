package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * ContraIndicacaoMedica.java
 * @author 20091BSI0273
 * Classe que representa as contra indicacoes medicas a doacoes de orgaos em uma notificacao
 */
@Entity
public class ContraIndicacaoMedica extends ObjetoPersistente {
	
	@Column
	private String nome;// nome ou identificacao da contra indicacao medica exs: Sepse descontrolada ou Sindrome do Choque Toxico; Soropositivo para HIV
	
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
