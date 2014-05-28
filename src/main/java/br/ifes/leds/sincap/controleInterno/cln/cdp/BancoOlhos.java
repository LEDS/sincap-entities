package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * BancoOlhos.java
 *
 * @author 200121BSI0252 Classe que representa um banco de olhos
 */
@Entity
@Getter
@Setter
public class BancoOlhos extends Instituicao {
    @Column
    private String cnes; //codigo que identifica o estabelecimento de saude
}
