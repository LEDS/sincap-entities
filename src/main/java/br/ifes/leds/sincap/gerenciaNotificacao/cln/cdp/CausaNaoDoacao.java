package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.validacao.annotations.ProblemasEstruturais;
import br.ifes.leds.sincap.validacao.annotations.RecusaFamiliar;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    @RecusaFamiliar(groups = {EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private TipoNaoDoacao tipoNaoDoacao;
}
