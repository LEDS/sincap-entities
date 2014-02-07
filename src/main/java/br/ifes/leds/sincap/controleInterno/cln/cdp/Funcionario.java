package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Funcionario
 * @author 20091BSI0273
 * Classe abstrata que representa um Funcionario, herda de Pessoa
 */
@MappedSuperclass
public abstract class Funcionario extends Pessoa {
	
	@OneToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
