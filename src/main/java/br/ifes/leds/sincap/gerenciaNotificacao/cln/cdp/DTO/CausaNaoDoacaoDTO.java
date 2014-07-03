package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CausaNaoDoacaoDTO {

    private Long id;
    private String nome;
    private String tipoNaoDoacao;
}
