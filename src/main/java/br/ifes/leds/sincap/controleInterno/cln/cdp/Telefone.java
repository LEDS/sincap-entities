package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.*;
import lombok.experimental.Builder;

/**
 * Telefone.java
 * @author 20091BSI0273
 * Classe que representa o telefone de uma Pessoa, herda de ObjetoPersistente.
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Telefone extends ObjetoPersistente {
	@Column
    @NotNull
	private String numero;
}