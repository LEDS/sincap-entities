package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import java.util.Set;
import javax.persistence.FetchType;

/**
 * Instituicao.java
 *
 * @author 20091BSI0273 Classe abstrata que representa uma Instituicao,
 */
@MappedSuperclass
public abstract class Instituicao {

    @Id
    @Column(name = "InstituicaoID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;//nome da instituicao segundo o site cnes
    @Column
    private String fantasia;//nome da instituicao segundo o site cnes
    @Column
    private String sigla;//sigla do nome da instituicao
    @Column
    private String email;//email da instituicao
    @OneToOne
    private Endereco endereco;//endereco da instituicao

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Telefone> telefones;//Telefone da instituicao

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setFantasia(String fantasia){
        this.fantasia = fantasia;
    }

    public String getFantasia(){
        return this.fantasia;
    }
}
