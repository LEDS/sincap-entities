/*
 * Pais.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */
package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import java.util.Set;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Pais extends ObjetoPersistente {
    @Column
    private String nome;
    
    @OneToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SELECT)
    private Set<Estado> estados;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Estado> getEstados() {
        return estados;
    }

    public void setEstados(Set<Estado> estados) {
        this.estados = estados;
    }
    
}
