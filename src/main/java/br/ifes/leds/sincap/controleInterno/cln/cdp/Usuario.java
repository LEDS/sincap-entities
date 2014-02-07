package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Usuario.java
 * @author 20091BSI0273
 * Classe que representa um Usuario do sistema, herda de ObjetoPersistente.
 */
@Entity
public class Usuario extends ObjetoPersistente {
	
	@Column (unique=true)
	private String username;// username ou nome de usuário

	@Column
	private String password;// password ou senha
	
	@Column
	private String email;// endereco de email
	
	@Column
	private boolean active;// ativo ou inativo
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
