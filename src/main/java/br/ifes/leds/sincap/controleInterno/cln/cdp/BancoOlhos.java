package br.ifes.leds.sincap.controleInterno.cln.cdp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * BancoOlhos.java
 *
 * @author 200121BSI0252 Classe que representa um banco de olhos
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BancoOlhos extends Instituicao {

    @OneToMany(mappedBy = "bancoOlhos")
    private List<Hospital> hospitais;
}
