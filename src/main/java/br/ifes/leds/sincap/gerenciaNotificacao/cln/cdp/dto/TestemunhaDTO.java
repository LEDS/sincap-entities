/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
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
public class TestemunhaDTO {

    private Long id;
    @Length(min = 5, max = 255)
    private String documentoSocial;
    @Length(min = 5, max = 255)
    private String nome;
    @Valid
    private Telefone telefone;
}
