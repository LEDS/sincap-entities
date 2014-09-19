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
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

/**
 * Obito.java
 * @author 20091BSI0273
 * Classe que representa o obito de um paciente
 */

@Setter
@Getter
@Entity
public class Obito extends ObjetoPersistente {
	
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataObito; //Data e horario do obito

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataCadastro; //Data e horario da notificação de obito
    
    @Column
    @NotNull
    private boolean aptoDoacao;

    @Enumerated (EnumType.STRING)
    private CorpoEncaminhamento corpoEncaminhamento;

    @OneToOne
    @NotNull
    private CausaMortis primeiraCausaMortis;
    
    @OneToOne
    @NotNull
    private CausaMortis segundaCausaMortis;
    
    @OneToOne
    private CausaMortis terceiraCausaMortis;
    
    @OneToOne
    private CausaMortis quartaCausaMortis;

    @JoinColumn(nullable = false)
    @OneToOne
    @NotNull
    private Paciente paciente;

    @OneToOne
    @NotNull
    private Setor setor;

    @OneToOne
    @NotNull
    private Hospital hospital;

    private TipoObito tipoObito;
}
