package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.CascadeType;
import org.hibernate.annotations.Cascade;

/**
 * CausaMortis.java
 * @author 20091BSI0273
 * Classe que representa o motivo que levou ao aobito de um paciente
 */
@Entity
public class CausaMortis extends ObjetoPersistente {
    @Column
    private String descricao;// descricao da causa do obto exs: Sepse de foco pulmonar ou Insuficiencia Respiratoria Aguda ou Sindrome do Choque Toxico

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
