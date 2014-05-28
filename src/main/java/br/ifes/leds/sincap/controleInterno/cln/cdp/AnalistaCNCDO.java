/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name="id")
@Table(name="AnalistaCNCDO")
public class AnalistaCNCDO extends Funcionario{
}
