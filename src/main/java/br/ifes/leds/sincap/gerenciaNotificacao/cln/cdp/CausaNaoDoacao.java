package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ifes.leds.sincap.validacao.annotations.ProblemasEstruturais;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Marcos Dias
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class CausaNaoDoacao extends ObjetoPersistente {

    @Column
    private String nome;

    @Enumerated(value = EnumType.STRING)
    @ProblemasEstruturais(groups = {EntrevistaNaoRealizada.class})
    private TipoNaoDoacao tipoNaoDoacao;
}
