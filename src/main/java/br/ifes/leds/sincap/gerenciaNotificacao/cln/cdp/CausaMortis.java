package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.*;

/**
 * CausaMortis.java
 * 
 * @author 20091BSI0273 Classe que representa o motivo que levou ao aobito de um
 *         paciente
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CausaMortis extends ObjetoPersistente {

    @Column
    @NotNull
    private String nome;
}
