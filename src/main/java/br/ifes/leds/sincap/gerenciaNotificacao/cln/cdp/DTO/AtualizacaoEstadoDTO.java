package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author marcosdias
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class AtualizacaoEstadoDTO {

    private Long id;
    private EstadoNotificacaoEnum estadoNotificacaoEnum;
    private Long funcionario;
}
