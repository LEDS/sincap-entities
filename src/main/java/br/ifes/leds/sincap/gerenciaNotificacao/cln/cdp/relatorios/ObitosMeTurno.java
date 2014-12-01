package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;

/**
 * Created by aleao on 01/12/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ObitosMeTurno {
    private String turno;
    private Long quantidade;
}
