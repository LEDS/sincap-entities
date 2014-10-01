/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.controleInterno.cln.cdp.dto;

import lombok.*;
import lombok.experimental.Builder;

/**
 *
 * @author 20121bsi0252
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UsuarioDTO {

    private String username;
    private String password;
    private String email;
    private Long hospital;

}
