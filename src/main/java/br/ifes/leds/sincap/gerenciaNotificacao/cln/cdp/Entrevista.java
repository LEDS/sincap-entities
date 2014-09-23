package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
    @NotNull
    @Past
    private Calendar dataCadastro; //Data e horario do cadastro entrevista.
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataEntrevista; //Data e horario de quando a entrevista foi realizada.
    
    @Column
    @NotNull
    private boolean entrevistaRealizada;

    @Column
    @NotNull
    private boolean doacaoAutorizada;
    
    @OneToOne
    @NotNull
    private Responsavel responsavel;
    
    @OneToOne
    private Testemunha testemunha1;
    
    @OneToOne
    private Testemunha testemunha2;
    
    @ManyToOne
    private Funcionario funcionario;
}
