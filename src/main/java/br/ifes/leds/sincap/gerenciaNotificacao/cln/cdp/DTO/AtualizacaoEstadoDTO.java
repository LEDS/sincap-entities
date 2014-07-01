package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;

/**
 *
 * @author marcosdias
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoEstadoDTO {

    private Long id;
    private EstadoNotificacaoEnum estadoNotificacao;
    private Long funcionario;
}
