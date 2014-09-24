package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * BancoOlhos.java
 *
 * @author 200121BSI0252 Classe que representa um banco de olhos
 */
@Entity
@Getter
@Setter
public class BancoOlhos extends Instituicao {

    @OneToMany(mappedBy = "bancoOlhos")
    private List<Hospital> hospitais;
}
