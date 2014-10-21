package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by aleao on 21/10/14.
 */
@Getter
@Setter
@Entity
public class TotalDoacaoInstituicao extends InstituicaoNotificadora{

    @Column
    private Long numeroNotificacao;
    @Column
    private Long numeroEntrevista;
    @Column
    private Long numeroRecusa;
    @Column
    private Long numeroDoacao;
    @Column
    private Long percentualEfetivacao;

}
