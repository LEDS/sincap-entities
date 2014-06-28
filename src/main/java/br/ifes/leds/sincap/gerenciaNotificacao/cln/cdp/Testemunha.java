package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;

/**
 * Testemunha.java
 *
 * @author 20091BSI0273 Classe que representa uma Testemunha da autorizacao de
 * doacao
 */
@Getter
@Setter
@Entity
public class Testemunha extends Pessoa {

    @Column
    private String cpf;
}