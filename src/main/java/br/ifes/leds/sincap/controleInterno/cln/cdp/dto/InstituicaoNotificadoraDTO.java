package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class InstituicaoNotificadoraDTO {
    private long id;
    private String nome;
    private String cnes;
    private String email;
    private String fantasia;
    private Telefone telefone;
    private Endereco endereco;
    private List<FuncionarioDTO> funcionarioDTOs;
}
