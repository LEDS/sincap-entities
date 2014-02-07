 package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;


/**
 * Paciente.java
 * @author 20091BSI0273
 * Classe abstrata que representa um paciente
 */
@Entity
public class Paciente extends Pessoa {
	
    @Temporal(TemporalType.DATE)
    private Calendar dataNascimento;// data de nascimento

    @Temporal(TemporalType.DATE)
    private Calendar dataInternacao;// data de internacao hospitalar do paciente

    @Column
    private String numeroProntuario;// numero do prontuario, ou registro de prontuario, 

    @Column
    private String nomeMae;// nome da mae do paciente
    
    @Column
    private String nomePai;// nome do pai do paciente
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;// Sexo do paciente

    @OneToOne
    @JoinColumn(nullable = true)//TODO rever regra de negocio
    @Cascade({CascadeType.SAVE_UPDATE})
    private Responsavel responsavel;// representa o responsavel pelo paciente

    @Column
    private String rg;// rg do paciente

    @Enumerated (EnumType.STRING)
    private TipoPaciente tipoPaciente;// tipo de paciente ex: crianca, adulto, nao-identificado, recem-nasciso
    
    @Enumerated (EnumType.STRING)
    private EstadoCivil EstadoCivil; // tipo de estados civis ex: SOLTEIRO, CASADO, DIVORCIADO, VIUVO

    @OneToOne
    private Doacao doacao;
    
    @Column
    private String nacionalidade;// nacionalidade do paciente
    
    
    //Gets e Sets
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }    
    
    public EstadoCivil getEstadoCivil() {
        return EstadoCivil;
    }
    public void setEstadoCivil(EstadoCivil EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }
    
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    
    public String getRg(){
        return rg;
    }
    public void setRg(String rg){
        this.rg = rg;
    }
    
    public String getNomePai() {
        return nomePai;
    }
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
    
    public Doacao getDoacao() {
        return doacao;
    }
    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }
    
    public TipoPaciente getTipoPaciente() {
        return tipoPaciente;
    }
    public void setTipoPaciente(TipoPaciente tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }
    
    public Responsavel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
    public Calendar getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Calendar getDataInternacao() {
        return dataInternacao;
    }
    public void setDataInternacao(Calendar dataInternacao) {
        this.dataInternacao = dataInternacao;
    }
    
    public String getNumeroProntuario() {
        return numeroProntuario;
    }
    public void setNumeroProntuario(String numeroProntuario) {
        this.numeroProntuario = numeroProntuario;
    }
    
    public String getNomeMae() {
        return nomeMae;
    }
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
}
