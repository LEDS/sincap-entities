package br.ifes.leds.reuse.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
/**
 * ObjetoPersistente.java
 * @author 20091BSI0273
 * Classe que representa um objeto persistente, ou seja, persistido no bd, arquivo, etc.
 */
@Setter
@Getter
@MappedSuperclass
@EqualsAndHashCode
public abstract class ObjetoPersistente implements Serializable {

	/**
	 * Generated Serial version Id
	 */
	private static final long serialVersionUID = 2763509368971189912L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
