package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Marcos Dias
 */
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class AtualizacaoEstado extends ObjetoPersistente {

    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoNotificacaoEnum estadoNotificacao;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar dataAtualizacaos;

    @ManyToOne
    @NotNull
    private Funcionario funcionario;
}
