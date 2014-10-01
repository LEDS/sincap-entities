package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import lombok.*;
import lombok.experimental.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CausaNaoDoacaoDTO {

    private Long id;
    private String nome;
    private TipoNaoDoacao tipoNaoDoacao;
}
