package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;

/**
 *
 * @author Marcos Dias
 */
@Getter
@Setter
@Entity
public class CausaNaoDoacao extends ObjetoPersistente {

    @Column
    private String nome;

    @Enumerated(value = EnumType.STRING)
    private TipoNaoDoacao tipoNaoDoacao;
}
