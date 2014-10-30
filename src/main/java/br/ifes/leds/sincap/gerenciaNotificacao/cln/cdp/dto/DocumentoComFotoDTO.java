package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto;
import lombok.*;
import lombok.experimental.Builder;
import org.hibernate.validator.constraints.Length;

/**
 * Created by marcosdias on 29/09/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DocumentoComFotoDTO {
    @Length(min = 3, max = 255)
    private String documento;
    private TipoDocumentoComFoto tipoDocumentoComFoto;
}
