package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.*;
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

    @Column
    private String religiao;

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
    private String registroHospitalar;

    @Length(min = 3, max = 255)
    @NotNull
    @Column
    private String nacionalidade;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentoComFoto documentoSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil EstadoCivil;
}
