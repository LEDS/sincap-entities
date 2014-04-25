/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author 20121BSI0252
 */
@Entity
@PrimaryKeyJoinColumn(name="id")
public class AnalistaCNCDO extends Funcionario{
    
    @Column
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    
    
}
