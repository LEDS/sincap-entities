package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Captacao.java
 * @author 20091BSI0273
 * Classe que representa a captacao de um determinado orgao
 */
@Entity
public class Captacao extends ObjetoPersistente {
	
	@Column
	private boolean realizada;//valor que diz se a captacao foi realizada ou nao
		
	public boolean isRealizada() {
		return realizada;
	}
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}	
}
