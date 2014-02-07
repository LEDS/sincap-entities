package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import javax.persistence.Entity;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Testemunha.java
 * @author 20091BSI0273
 * Classe que representa uma Testemunha da autorizacao de doacao
 */
@Entity
public class Testemunha extends Pessoa {
    
    @Column
    private String cpf;// cpf do Testemunha
    
    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    private Endereco endereco; // endereco do Testemunha

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
    
    
}
