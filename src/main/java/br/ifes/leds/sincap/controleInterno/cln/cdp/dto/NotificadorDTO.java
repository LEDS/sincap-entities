package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificadorDTO extends FuncionarioDTO{
    private List<Long> instuicoesNotificadoras;
}
