package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorDTO {

    private Long id;
    private String nome;
    private boolean usado;
}
