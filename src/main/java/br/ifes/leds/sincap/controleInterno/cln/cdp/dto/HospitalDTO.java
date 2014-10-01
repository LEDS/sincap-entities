package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.*;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import lombok.experimental.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class HospitalDTO {

    private Long id;
    private String nome;
    private String cnes;
    private String email;
    private String fantasia;
    private Endereco endereco;
    private Telefone telefone;
    private String sigla;
}
