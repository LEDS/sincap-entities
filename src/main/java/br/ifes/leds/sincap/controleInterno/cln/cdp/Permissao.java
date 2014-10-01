package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permissao extends ObjetoPersistente {

    @NotNull
    @Column(unique = true)
    private String role;
}
