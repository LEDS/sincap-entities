package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * Created by marcosdias on 29/09/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoComFotoDTO {
    private String documento;
    private TipoDocumentoComFoto tipoDocumentoComFoto;
}
