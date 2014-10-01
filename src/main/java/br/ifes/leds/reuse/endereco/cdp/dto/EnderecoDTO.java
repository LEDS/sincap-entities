package br.ifes.leds.reuse.endereco.cdp.dto;

import lombok.*;
import lombok.experimental.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private Long bairro;
    private Long cidade;
    private Long estado;
    private String cep;
}
