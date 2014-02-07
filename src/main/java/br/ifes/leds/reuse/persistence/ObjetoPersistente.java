package br.ifes.leds.reuse.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/**
 * ObjetoPersistente.java
 * @author 20091BSI0273
 * Classe que representa um objeto persistente, ou seja, persistido no bd, arquivo, etc.
 */
@MappedSuperclass
public abstract class ObjetoPersistente implements Serializable {

	/**
	 * Generated Serial version Id
	 */
	private static final long serialVersionUID = 2763509368971189912L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjetoPersistente) {
            ObjetoPersistente o = (ObjetoPersistente) obj;
            if (this.id != null && this.id == o.id) {
                return true;
            }
        }
        return false;
    }
	
}
