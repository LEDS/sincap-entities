package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ObitoInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;

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
    @NotNull
    @Past
    private Calendar dataObito; //Data e horario do obito

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Calendar dataCadastro; //Data e horario da notificação de obito
    
    @Column
    @NotNull
    private boolean aptoDoacao;

    @Enumerated (EnumType.STRING)
    private CorpoEncaminhamento corpoEncaminhamento;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @NotNull
    private CausaMortis primeiraCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private CausaMortis segundaCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CausaMortis terceiraCausaMortis;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CausaMortis quartaCausaMortis;

    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private Paciente paciente;

    @OneToOne
    @NotNull
    private Setor setor;

    @OneToOne
    @NotNull
    private Hospital hospital;

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
}
