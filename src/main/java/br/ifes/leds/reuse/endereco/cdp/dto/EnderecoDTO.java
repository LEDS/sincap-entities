package br.ifes.leds.reuse.endereco.cdp.dto;

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
