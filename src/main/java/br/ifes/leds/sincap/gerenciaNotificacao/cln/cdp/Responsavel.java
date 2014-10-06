package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TelefonesResponsavelInterface;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String profissao;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String religiao;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String grauEscolaridade;

    @Size(min = 3, max = 255)
    @Column
    @NotNull
    private String documentoSocial;
    
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
