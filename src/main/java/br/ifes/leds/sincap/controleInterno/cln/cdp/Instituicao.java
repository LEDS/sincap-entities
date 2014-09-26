package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.*;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import java.util.Set;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.Getter;
import lombok.Setter;

/**
 * Instituicao.java
 *
 * @author 20091BSI0273 Classe abstrata que representa uma Instituicao,
 */
@Setter
@Getter
@MappedSuperclass
public abstract class Instituicao extends ObjetoPersistente {

    @Column(unique=true)
    private String nome;
    
    @Column
    private String cnes;
    
    @Column
    private String email;
    
    @Column
    private String fantasia;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Telefone telefone;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Funcionario> funcionarios;
}