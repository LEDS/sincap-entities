/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cdp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 * @author 20121BSI0252
 */
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Captador extends Funcionario {
    @OneToOne
    @NotNull
    private BancoOlhos bancoOlhos;
}