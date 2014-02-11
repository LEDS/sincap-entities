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
import javax.persistence.ManyToMany;

/**
 * Doacao.javax
 * @author 20091BSI0273
 * Classe que representa a doacao ou Termo de doacao de Orgaos, 
 */
@Entity
public class Doacao extends ObjetoPersistente {
	
    @Column
    private boolean autorizada;	//se foi autorizado ou nao
    
    @ManyToMany
    private Set<MotivoRecusa> recusaFamiliar = new HashSet<MotivoRecusa>(); // motivos de recusa do tipo Recusa Familiar
    
    @ManyToMany
    private Set<MotivoRecusa> contraIndicacaoMedica = new HashSet<MotivoRecusa>(); //motivos de recusa do tipo contra-indicacao medica
    
    @OneToMany
    private Set <Responsavel> responsaveis = new HashSet<Responsavel>();// responsavel do paciente que autorizou, 2 se for menor de idade

    @OneToMany
    private Set<Testemunha> testemunhas = new HashSet<Testemunha>();// testemunhas da autorizacao da doacao

    @Column
    private Captacao captacao;

    @Temporal(TemporalType.DATE)
    private Calendar dataEntrevista;

    public boolean isAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Set<MotivoRecusa> getRecusaFamiliar() {
        return recusaFamiliar;
    }

    public void setRecusaFamiliar(Set<MotivoRecusa> recusaFamiliar) {
        this.recusaFamiliar = recusaFamiliar;
    }

    public Set<MotivoRecusa> getContraIndicacaoMedica() {
        return contraIndicacaoMedica;
    }

    public void setContraIndicacaoMedica(Set<MotivoRecusa> contraIndicacaoMedica) {
        this.contraIndicacaoMedica = contraIndicacaoMedica;
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
	
}
