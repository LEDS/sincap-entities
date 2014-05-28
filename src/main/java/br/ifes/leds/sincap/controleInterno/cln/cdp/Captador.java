/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 20121BSI0252
 */
@Getter
@Setter
@Entity
@Table(name="Captador")
public class Captador extends Funcionario{
    @OneToOne
    private BancoOlhos bancoOlhos;
}