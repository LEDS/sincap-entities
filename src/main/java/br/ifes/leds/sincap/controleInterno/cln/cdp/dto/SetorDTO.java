package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.*;
import lombok.experimental.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SetorDTO {

    private Long id;
    private String nome;
    private boolean usado;
}
