package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;

/**
 * Created by aleao on 03/11/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class QualificacaoRecusaFamiliar {
    private Long codigo;
    private String nomeRecusa;
    private Double total;
}
