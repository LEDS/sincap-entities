package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import java.util.Calendar;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

/**
 * Captacao.java
 *
 * @author 20091BSI0273 Classe que representa a captacao de um determinado orgao
 */
@Getter
@Setter
@Entity
public class Captacao extends ObjetoPersistente {

    @Column
    @NotNull
    private boolean captacaoRealizada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @NotNull
    private Calendar dataCaptacao; //Data e horario do captacao
    
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @NotNull
    private Calendar dataCadastro; //Data e horario da notificação de captacao
    
    @OneToOne
    @NotNull
    private Captador captador;
    
    @Column
    private String comentario;
 }
