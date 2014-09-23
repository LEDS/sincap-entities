package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permissao extends ObjetoPersistente {

    @Column
    @NotNull
    private String role;
}
