package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.dto.FuncionarioDTO;
import lombok.*;
import lombok.experimental.Builder;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ComentarioDTO {

    private FuncionarioDTO funcionario;

    private Long processo;

    private Calendar dataComentario;

    private String momento;

    private String descricao;
}
