/*
 * Estado.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Entity;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Estado extends ObjetoPersistente {
    
    @Column
    private String sigla;
    
    @Column
    private String nome;
    
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Cidade> cidades;
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Set<Cidade> getCidades() {
        return cidades;
    }
    
    public void setCidades(Set<Cidade> cidades) {
        this.cidades = cidades;
    }
    
}