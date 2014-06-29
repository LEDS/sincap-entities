package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
