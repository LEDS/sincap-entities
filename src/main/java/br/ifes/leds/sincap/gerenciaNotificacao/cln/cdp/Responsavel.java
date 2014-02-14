package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Responsavel.java
 * @author 20091BSI0273
 * Classe que representa uma pessoa responsavel por um paciente
 */
@Entity
public class Responsavel extends Pessoa {
	
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;// representa o grau de parentesco

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;// representa o estado civil
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;// Sexo do Responsavel
    
    @Column
    private String rg;// rg do responsavel
    
    @Column
    private String profissao;// profissao do responsavel
    
    // TODO telefone e endereco não devem de ser comentarios
    @OneToMany(fetch = FetchType.EAGER)
    private List<Telefone> telefones =  new ArrayList<>(); // Lista de telefones do responsavel
    
    @OneToOne
    private Endereco endereco; // Endereco do responsavel
    
    @Column
    private String nacionalidade;// nacionalidade do responsavel
    
    // Gets e Sets
    public String getNacionalidade() {
            return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    
    public Sexo getSexo() {
            return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }    
    public Parentesco getParentesco() {
            return parentesco;
    }
    public void setParentesco(Parentesco parentesco) {
            this.parentesco = parentesco;
    }
    public EstadoCivil getEstadoCivil() {
            return estadoCivil;
    }
    public void setEstadoCivil(EstadoCivil estadoCivil) {
            this.estadoCivil = estadoCivil;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    
}
