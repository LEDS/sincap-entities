package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.EntrevistaInterface;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import java.util.Calendar;

/**
 *
 * @author Marcos Dias
 */
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Entrevista extends ObjetoPersistente implements EntrevistaInterface {
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataCadastro; //Data e horario do cadastro entrevista.
    
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @Null(message = "{EntrevistaValida.dataEntrevista}", groups = {EntrevistaNaoRealizada.class})
    private Calendar dataEntrevista; //Data e horario de quando a entrevista foi realizada.
    
    @Column
    @NotNull
    private boolean entrevistaRealizada;

    @Column
    @NotNull
    private boolean doacaoAutorizada;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    @Null(message = "{EntrevistaValida.responsavel1}", groups = {EntrevistaNaoRealizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private Responsavel responsavel;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    @Null(message = "{EntrevistaValida.responsavel2}", groups = {EntrevistaNaoRealizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private Responsavel responsavel2;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Null(message = "{EntrevistaValida.testemunha1}", groups = {EntrevistaNaoRealizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private Testemunha testemunha1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    @Null(message = "{EntrevistaValida.testemunha2}", groups = {EntrevistaNaoRealizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private Testemunha testemunha2;
    
    @ManyToOne
    private Funcionario funcionario;

    @Override
    public boolean haCausaNaoDoacao() {
        return !this.isDoacaoAutorizada() || !this.isEntrevistaRealizada();
    }
}
