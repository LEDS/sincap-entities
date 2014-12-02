package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;

import java.math.BigDecimal;

/**
 * @author aleao on 18/11/14.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class FaixaEtaria {

    private String faixa;
    private Long totalPCR;
    private Long totalME;
    private BigDecimal percentualPCR;
    private BigDecimal percentualME;

}
