package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

import java.util.Calendar;

/**
 * DTO para a classe de Captacao.
 *
 * @author Lucas Possatti
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptacaoDTO {

    private Long id;
    private boolean captacaoRealizada;
    private Calendar dataCaptacao; //Data e horario do captacao
    private Calendar dataCadastro; //Data e horario da notificação de captacao
    private Long captador;
    private String comentario;
}
