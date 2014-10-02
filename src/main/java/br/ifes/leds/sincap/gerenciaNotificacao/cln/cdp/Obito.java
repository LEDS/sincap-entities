package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.DataCadastro;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;

/**
 * Obito.java
 * @author 20091BSI0273
 * Classe que representa o obito de um paciente
 */

@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Obito extends ObjetoPersistente implements DataCadastro {
	
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
}
