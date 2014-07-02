package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import java.util.Calendar;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private boolean captacaoRealizada;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCaptacao; //Data e horario do captacao
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro; //Data e horario da notificação de captacao
    
    @OneToOne
    private Captador captador;
    
    @Column
    private String comentario;
 }
