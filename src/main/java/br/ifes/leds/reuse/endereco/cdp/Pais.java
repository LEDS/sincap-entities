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
import java.util.List;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Pais extends ObjetoPersistente {
    @Column
    private String nome;
    
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
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
