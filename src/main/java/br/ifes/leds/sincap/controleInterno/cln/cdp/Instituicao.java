package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
public abstract class Instituicao {

    @Id
    @Column(name = "InstituicaoID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String nome;
    
    @Column
    private String cnes;
    
    @Column
    private String email;
    
    @Column
    private String fantasia;
    
    @OneToOne
    private Endereco endereco;
    
    @OneToOne
    private Telefone telefone;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Funcionario> funcionarios;
}