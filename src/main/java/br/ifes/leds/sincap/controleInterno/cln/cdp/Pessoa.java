package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Pessoa.java
 *
 * @author 20091BSI0273
 *         Classe abstrata que representa uma Pessoa, herda de objetoPersistente.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa extends ObjetoPersistente {

    @Column
    private String nome;

    @OneToOne
    @JoinColumn(nullable = true)
    private Telefone telefone;

    @OneToOne
    @JoinColumn(nullable = true)
    private Endereco endereco;
}
