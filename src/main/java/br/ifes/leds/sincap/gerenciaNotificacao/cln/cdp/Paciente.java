package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.PacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import org.hibernate.validator.constraints.Length;


/**
 * Paciente.java
 *
 * @author 20091BSI0273
 *         Classe abstrata que representa um paciente
 */
@Setter
@Getter
@Entity
@DatasPacienteConsistentes
@EqualsAndHashCode(callSuper = true)
public class Paciente extends Pessoa implements PacienteInterface {

    @Past
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInternacao;

    @Past
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataNascimento;

    @Column
    @NotNull
    private String profissao;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String nomeMae;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String numeroProntuario;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String numeroSUS;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String nacionalidade;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String documentoSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil EstadoCivil;
}
