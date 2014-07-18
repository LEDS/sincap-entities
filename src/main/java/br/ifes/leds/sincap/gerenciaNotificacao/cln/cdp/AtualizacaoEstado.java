package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Marcos Dias
 */
@Setter
@Getter
@Entity
public class AtualizacaoEstado extends ObjetoPersistente {

    @Enumerated(EnumType.STRING)
    private EstadoNotificacaoEnum estadoNotificacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAtualizacaos;

    @ManyToOne
    private Funcionario funcionario;
}
