package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author marcosdias
 */
@Getter
@Setter
class AtualizacaoEstadoDTO {
    private EstadoNotificacaoEnum estadoNotificacaoEnum;
    private Long funcionario;
}
