package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CausaMortis extends ObjetoPersistente {

    @Column
    @NotNull
    private String nome;
}
