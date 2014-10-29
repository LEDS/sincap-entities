package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;


/**
 * @author AAguiar on 29/10/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TotalNaoDoacaoInstituicao {

    private String nome;

    private Integer recusaFamiliar;

    private Integer contraInd;

    private Integer problema;

    private Integer total;


}
