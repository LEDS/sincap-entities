package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.PacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.obito.EtapaObito;
import br.ifes.leds.sincap.validacao.groups.obito.ObitoPNI;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.groups.Default;
import java.util.Calendar;


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

    @Past(groups = {Default.class, EtapaObito.class, EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @NotNull(groups = {Default.class, EtapaObito.class, EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInternacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Past(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class,ObitoPNI.class})
    private Calendar dataNascimento;

    @Column
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    private String profissao;

    @Length(min = 3, max = 255)
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class,ObitoPNI.class})
    @Column
    private String nomeMae;

    @Column
    private String religiao;

    @Length(min = 3, max = 255)
    @NotNull(groups = {Default.class, EtapaObito.class, EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @Column
    private String numeroProntuario;

    @Length(min = 3, max = 255, groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class,ObitoPNI.class})
    @Column
    private String numeroSUS;

    @Length(min = 3, max = 255, groups = {Default.class, EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    @Column
    private String nacionalidade;

    @Valid
    @NotNull(groups = {EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class, EtapaObito.class})
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentoComFoto documentoSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil EstadoCivil;
}
