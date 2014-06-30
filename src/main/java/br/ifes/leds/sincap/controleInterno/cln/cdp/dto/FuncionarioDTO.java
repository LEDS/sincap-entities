package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
public class FuncionarioDTO {
    private String senha;
    private boolean ativo;
    private String cpf;
    private String documentoSocial;
    private String email;
}
