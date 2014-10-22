package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;

/**
 * @author aleao on 21/10/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TotalDoacaoInstituicao {

    private String nomeInstituicao;

    private Integer numeroNotificacao;

    private Integer numeroEntrevista;

    private Integer numeroRecusa;

    private Integer numeroDoacao;

    private Double percentualEfetivacao;

}
