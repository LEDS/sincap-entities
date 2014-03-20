/*
 * M.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import java.util.List;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Cidade extends ObjetoPersistente {
    
    @Column
    private String nome;
    
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.JOIN)
    private Set<Bairro> bairros;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Set<Bairro> getBairros() {
        return bairros;
    }
    
    public void setBairros(Set<Bairro> bairros) {
        this.bairros = bairros;
    }
}
