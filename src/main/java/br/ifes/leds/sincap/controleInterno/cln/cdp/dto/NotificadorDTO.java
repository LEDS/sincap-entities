package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
public class NotificadorDTO {
    private List<InstituicaoNotificadoraDTO> instuicoesNotifcadoras;
}
