package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import lombok.experimental.Builder;

import java.util.Calendar;

/**
 *
 * @author marcosdias
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizacaoEstadoDTO {

    private Long id;
    private EstadoNotificacaoEnum estadoNotificacao;
    private Calendar dataAtualizacaos;
    private Long funcionario;
}
