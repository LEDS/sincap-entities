package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Enumerated (EnumType.STRING)
    private EstadoNotificacaoEnum estadoNotificacao;
    
    @ManyToOne
    private Funcionario funcionario;
}
