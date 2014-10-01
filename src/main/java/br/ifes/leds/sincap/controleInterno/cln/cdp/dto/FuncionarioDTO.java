package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.DocumentoComFotoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * @author 20102bsi0553
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuncionarioDTO {
    private String senha;
    private boolean ativo;
    private String cpf;
    private DocumentoComFotoDTO documentoSocial;
    private String email;
}
