package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;

/**
 *
 * @author Marcos Dias
 */
@Setter
@Getter
@Entity
public class Entrevista extends ObjetoPersistente {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataEntrevista; //Data e horario do entrevista
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataEvento; //Data e horario da notificação Doacao(entrevista)
    
    @Column
    private boolean doacaoAutorizada;
    
    @OneToOne
    private Responsavel responsavel;
    
    @OneToOne
    private Testemunha testemunha1;
    
    @OneToOne
    private Testemunha testemunha2;
    
    @ManyToOne
    private Funcionario funcionario;
}
