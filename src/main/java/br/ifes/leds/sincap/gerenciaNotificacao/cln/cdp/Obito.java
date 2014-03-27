package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 * Obito.java
 * @author 20091BSI0273
 * Classe que representa o obito de um paciente
 */
@Entity
public class Obito extends ObjetoPersistente {
	
    @Temporal(TemporalType.DATE)
    private Calendar dataObito;//representa data e horario do obito

    @Enumerated (EnumType.ORDINAL)
    private Encaminhamento encaminhamento;// representa se o corpo foi encaminhado ou nao e para onde.

    @OneToOne
    private CausaMortis primeiraCausaMortis;//representa o motivo que ocasionou o obito ou causa de obito;
    @OneToOne
    private CausaMortis segundaCausaMortis;//representa o motivo que ocasionou o obito ou causa de obito;
    @OneToOne
    private CausaMortis terceiraCausaMortis;//representa o motivo que ocasionou o obito ou causa de obito;
    @OneToOne
    private CausaMortis quartaCausaMortis;//representa o motivo que ocasionou o obito ou causa de obito;
    
    @JoinColumn(nullable = true)
    @OneToOne
    private Responsavel responsavel;// responsavel pelo obito

    @JoinColumn(nullable = true)
    @OneToOne
    private Paciente paciente;// representa o paciente que morreu

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
    public Calendar getDataObito() {
        return dataObito;
    }

    public void setDataObito(Calendar dataObito) {
        this.dataObito = dataObito;
    }

    public Encaminhamento getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(Encaminhamento encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public CausaMortis getPrimeiraCausaMortis() {
        return primeiraCausaMortis;
    }

    public void setPrimeiraCausaMortis(CausaMortis primeiraCausaMortis) {
        this.primeiraCausaMortis = primeiraCausaMortis;
    }

    public CausaMortis getSegundaCausaMortis() {
        return segundaCausaMortis;
    }

    public void setSegundaCausaMortis(CausaMortis segundaCausaMortis) {
        this.segundaCausaMortis = segundaCausaMortis;
    }

    public CausaMortis getTerceiraCausaMortis() {
        return terceiraCausaMortis;
    }

    public void setTerceiraCausaMortis(CausaMortis terceiraCausaMortis) {
        this.terceiraCausaMortis = terceiraCausaMortis;
    }

    public CausaMortis getQuartaCausaMortis() {
        return quartaCausaMortis;
    }

    public void setQuartaCausaMortis(CausaMortis quartaCausaMortis) {
        this.quartaCausaMortis = quartaCausaMortis;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
