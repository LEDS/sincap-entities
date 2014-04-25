package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Funcionario
 *
 * @author 20091BSI0273 Classe abstrata que representa um Funcionario, herda de
 * Pessoa
 */
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Funcionario extends Pessoa {

    @Column
    private String cpf;
    @Column
    private String documentoSocial;
    @Column
    private String email;
    @Column
    private String senha;// password ou senha
    @Column
    private boolean active;// ativo ou inativo
    @OneToMany
    private Set<Telefone> telefones;
   
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    
    public String getCpf() {
        return cpf;
    }

    public String getDocumentoSocial() {
        return documentoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDocumentoSocial(String documentoSocial) {
        this.documentoSocial = documentoSocial;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
}
