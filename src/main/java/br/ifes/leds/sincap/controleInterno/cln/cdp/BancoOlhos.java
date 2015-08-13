package br.ifes.leds.sincap.controleInterno.cln.cdp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * BancoOlhos.java
 *
 * @author 200121BSI0252 Classe que representa um banco de olhos
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true,exclude={"id","hospitais"})
public class BancoOlhos extends Instituicao {

    @OneToMany(mappedBy = "bancoOlhos", fetch = EAGER)
    private List<Hospital> hospitais;
}
