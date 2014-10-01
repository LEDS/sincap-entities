package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.*;
import lombok.experimental.Builder;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class FuncionarioDTO {
    private String senha;
    private boolean ativo;
    private String cpf;
    private String documentoSocial;
    private String email;
}
