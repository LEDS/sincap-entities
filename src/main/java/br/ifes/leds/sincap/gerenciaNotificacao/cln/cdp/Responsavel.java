package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TelefonesResponsavelInterface;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Calendar;

/**
 * Responsavel.java
 * @author 20091BSI0273
 * Classe que representa uma pessoa responsavel por um paciente
 */
@Getter
@Setter
@Entity
@TelefoneResponsavelConsistentes
@EqualsAndHashCode(callSuper = true)
public class Responsavel extends Pessoa implements TelefonesResponsavelInterface {

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String nacionalidade;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar dataNascimento;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String profissao;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentoComFoto documentoSocial;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String religiao;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String grauEscolaridade;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoCivil estadoCivil;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private Parentesco parentesco;
    
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Telefone telefone2;
}
