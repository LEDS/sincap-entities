package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by marcosdias on 29/09/14.
 */
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class DocumentoComFoto extends ObjetoPersistente {
    @Column
    @NotNull
    @Size(min = 3, max = 255)
    private String documento;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDocumentoComFoto tipoDocumentoComFoto;
}
