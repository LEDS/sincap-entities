package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.ManyToOne;

/**
 * MotivoRecusa.java
 *
 * @author 20091BSI0273 Classe que representa as causas de recusa familiar de
 * doacao de orgaos de um paciente
 */
@Entity
public class MotivoRecusa extends ObjetoPersistente {

    @Column
    private String nome;// representa o nome/descriacao de uma recusa familiar

    @ManyToOne
    private TipoMotivoRecusa tipoMotivoRecusa;

    public TipoMotivoRecusa getTipoMotivoRecusa() {
        return tipoMotivoRecusa;
    }

    public void setTipoMotivoRecusa(TipoMotivoRecusa tipoMotivoRecusa) {
        this.tipoMotivoRecusa = tipoMotivoRecusa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
