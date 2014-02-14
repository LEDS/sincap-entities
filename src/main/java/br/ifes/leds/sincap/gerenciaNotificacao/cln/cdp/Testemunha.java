package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Entity;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import javax.persistence.Column;

/**
 * Testemunha.java
 *
 * @author 20091BSI0273 Classe que representa uma Testemunha da autorizacao de
 * doacao
 */
@Entity
public class Testemunha extends Pessoa {

    @Column
    private String cpf;// cpf do Testemunha

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
