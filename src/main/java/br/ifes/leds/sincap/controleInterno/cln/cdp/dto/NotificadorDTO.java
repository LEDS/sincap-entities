package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.*;

import java.util.List;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificadorDTO extends FuncionarioDTO{
    private List<Long> instuicoesNotificadoras;
}
