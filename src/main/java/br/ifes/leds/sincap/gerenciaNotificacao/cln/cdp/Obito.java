package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ObitoInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.obito.EtapaObito;
import br.ifes.leds.sincap.validacao.groups.obito.ObitoEncaminhado;
import br.ifes.leds.sincap.validacao.groups.obito.ObitoNaoEncaminhado;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.groups.Default;
import java.util.Calendar;

import static javax.persistence.EnumType.STRING;
import static org.joda.time.Hours.hoursBetween;
import static org.joda.time.Years.yearsBetween;

/**
 * Obito.java
 * @author 20091BSI0273
 * Classe que representa o obito de um paciente
 */

@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
@DatasObitoConsistentes
public class Obito extends ObjetoPersistente implements ObitoInterface {
	
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(groups = {Default.class, EtapaObito.class})
    @Past
    private Calendar dataObito; //Data e horario do obito

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataCadastro; //Data e horario da notificação de obito
    
    @Column
    @NotNull
    private boolean aptoDoacao;

    @Enumerated (STRING)
    @NotNull(groups = {Default.class, EtapaObito.class})
    private CorpoEncaminhamento corpoEncaminhamento;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @NotNull(message ="{ObitoValido.naoEncaminhado}",groups = ObitoNaoEncaminhado.class)
    @Null(message ="{ObitoValido.encaminhado}", groups = ObitoEncaminhado.class)
    private CausaMortis primeiraCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    @Null(message ="{ObitoValido.encaminhado}", groups = ObitoEncaminhado.class)
    private CausaMortis segundaCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Null(message ="{ObitoValido.encaminhado}", groups = ObitoEncaminhado.class)
    private CausaMortis terceiraCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Null(message ="{ObitoValido.encaminhado}", groups = ObitoEncaminhado.class)
    private CausaMortis quartaCausaMortis;

    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    @NotNull.List({
            @NotNull,
            @NotNull(message = "{EntrevistaValida.semDadosPaciente}", groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    })
    private Paciente paciente;

    @OneToOne
    @NotNull(groups = {Default.class, EtapaObito.class})
    private Setor setor;

    @OneToOne
    @NotNull
    private Hospital hospital;

    @NotNull(groups = EtapaObito.class)
    private TipoObito tipoObito;

    @Override
    public boolean haCausaNaoDoacao() {
        return !this.isAptoDoacao();
    }

    @Override
    public Integer getIdadePaciente() {
        if (this.paciente == null || this.paciente.getDataNascimento() == null || this.dataObito == null) {
            return null;
        } else {
            DateTime dataNascimento = new DateTime(this.paciente.getDataNascimento());
            DateTime dataObito = new DateTime(this.dataObito);
            return yearsBetween(dataNascimento, dataObito).getYears();
        }
    }

    @Override
    public Integer getHorasObito() {
        if (this.dataObito == null) {
            return null;
        } else {
            return hoursBetween(new DateTime(this.dataObito), new DateTime()).getHours();
        }
    }
}
