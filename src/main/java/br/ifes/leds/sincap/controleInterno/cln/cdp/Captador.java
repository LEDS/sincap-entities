/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author 20121BSI0252
 */
@Entity
public class Captador extends Funcionario{
    @OneToOne
    private BancoOlhos bancoOlhos;

    public BancoOlhos getBancoOlhos() {
        return bancoOlhos;
    }

    public void setBancoOlhos(BancoOlhos bancoOlhos) {
        this.bancoOlhos = bancoOlhos;
    }
}