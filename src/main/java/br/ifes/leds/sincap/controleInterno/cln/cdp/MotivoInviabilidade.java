package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * MotivoInviabilidade.java
 * 
 * @author 20112BSI0083 Classe que representa um motivo de inviabilidade que
 *         impede a doacao
 */
@Entity
public class MotivoInviabilidade extends ObjetoPersistente {

	@ManyToOne
	private TipoMotivoInviabilidade tipoMotivoInviabilidade;
	@Column
	private String nome;
	
	public TipoMotivoInviabilidade getTipoMotivo() {
		return tipoMotivoInviabilidade;
	}
	public void setTipoMotivo(TipoMotivoInviabilidade tipoMotivoInviabilidade) {
		this.tipoMotivoInviabilidade = tipoMotivoInviabilidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
