/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.reuse.endereco.cdp.dto.EnderecoDTO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TelefonesResponsavelInterface;
import br.ifes.leds.sincap.validacao.annotations.TelefoneResponsavelConsistentes;
import lombok.*;
import lombok.experimental.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leds-6752
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@TelefoneResponsavelConsistentes
public class ResponsavelDTO implements TelefonesResponsavelInterface {

    private Long id;
    @Length(min = 5, max = 255)
    private String nome;
    @Length(min = 5, max = 255)
    private String nacionalidade;
    @Length(min = 5, max = 255)
    private String profissao;
    @Length(min = 5, max = 255)
    private String documentoSocial;
    @NotNull
    private EstadoCivil estadoCivil;
    private Sexo sexo;
    @NotNull
    private Parentesco parentesco;
    @Valid
    @NotNull
    private Telefone telefone;
    @Valid
    @NotNull
    private Telefone telefone2;
    @NotNull
    private EnderecoDTO endereco;
}
