package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Marcos Dias
 */

@Setter
@Getter
@Entity
public class AtualizacaoEstado extends ObjetoPersistente {    

    @Enumerated (EnumType.STRING)
    private EstadoNotificacaoEnum estadoNotificacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAtualizacaos;
    
    @ManyToOne
    private Funcionario funcionario;
}
