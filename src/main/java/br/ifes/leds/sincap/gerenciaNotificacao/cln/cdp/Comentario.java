package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * Created by aleao on 24/03/2015.
 */
@Setter
@Getter
@Entity
public class Comentario extends ObjetoPersistente  {

    @ManyToOne
    @NotNull
    private ProcessoNotificacao processo;

    @OneToOne
    private Funcionario funcionario;

    @Column
    private Calendar dataComentario;

    @Column
    private String descricao;
}
