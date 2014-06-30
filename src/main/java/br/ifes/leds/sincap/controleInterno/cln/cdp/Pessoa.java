package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Pessoa.java
 * @author 20091BSI0273
 * Classe abstrata que representa uma Pessoa, herda de objetoPersistente.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa extends ObjetoPersistente{
    
	@Column
	private String nome;
        
        @OneToOne
        @JoinColumn(nullable = false)
        private Telefone telefone;
        
        @OneToOne
        @JoinColumn(nullable = false)
        private Endereco endereco;
}
