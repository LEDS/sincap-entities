package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;


// Classe que representa o relatorio de obito por causa de parada cardiorespiratoria.

import lombok.*;
import lombok.experimental.Builder;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ObitoCardio
{
    private String nome;
    private Integer total;
    private BigDecimal percentual;
}
