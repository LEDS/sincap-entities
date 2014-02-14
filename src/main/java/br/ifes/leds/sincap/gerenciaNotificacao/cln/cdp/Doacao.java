package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 * Doacao.javax
 *
 * @author 20091BSI0273 Classe que representa a doacao ou Termo de doacao de
 * Orgaos,
 */
@Entity
public class Doacao extends ObjetoPersistente {

    @Column
    private boolean autorizada;	//se foi autorizado ou nao

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @ManyToMany
    private Set<MotivoRecusa> motivoRecusa = new HashSet<MotivoRecusa>(); // motivos de recusa do tipo Recusa Familiar

    @OneToMany
    private Set<Responsavel> responsaveis = new HashSet<Responsavel>();// responsavel do paciente que autorizou, 2 se for menor de idade

    @OneToMany
    private Set<Testemunha> testemunhas = new HashSet<Testemunha>();// testemunhas da autorizacao da doacao

    @OneToOne
    private Captacao captacao;

    @Temporal(TemporalType.DATE)
    private Calendar dataEntrevista;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar hrEntrevista;

    //Gets e Sets
    public Calendar getHrEntrevista() {
        return hrEntrevista;
    }

    public void setHrEntrevista(Calendar hrEntrevista) {
        this.hrEntrevista = hrEntrevista;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public boolean isAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Calendar getDataEntrevista() {
        return dataEntrevista;
    }

    public void setDataEntrevista(Calendar dataEntrevista) {
        this.dataEntrevista = dataEntrevista;
    }

    public Captacao getCaptacao() {
        return captacao;
    }

    public void setCaptacao(Captacao captacao) {
        this.captacao = captacao;
    }

    public Set<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(Set<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public Set<Testemunha> getTestemunhas() {
        return testemunhas;
    }

    public void setTestemunhas(Set<Testemunha> testemunhas) {
        this.testemunhas = testemunhas;
    }

    public Set<MotivoRecusa> getMotivoRecusa() {
        return motivoRecusa;
    }

    public void setMotivoRecusa(Set<MotivoRecusa> motivoRecusa) {
        this.motivoRecusa = motivoRecusa;
    }

    public void addMotivoRecusa(MotivoRecusa motivoRecusa){
           this.motivoRecusa.add(motivoRecusa);
    }
    
    public void addResponsavel(Responsavel responsavel){
           this.responsaveis.add(responsavel);
    }
    
    public void addTestemunha(Testemunha testemunha){
           this.testemunhas.add(testemunha);
    }

}
