package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CausaNaoDoacaoDTO {

    private Long id;
    private String nome;
    private TipoNaoDoacao tipoNaoDoacao;
}
