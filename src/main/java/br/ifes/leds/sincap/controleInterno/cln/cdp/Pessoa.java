package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Telefone telefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Endereco endereco;
}
