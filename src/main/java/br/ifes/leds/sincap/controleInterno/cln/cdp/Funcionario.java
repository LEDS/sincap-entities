package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

/**
 * Funcionario
 *
 * @author 20091BSI0273 Classe abstrata que representa um Funcionario, herda de
 * Pessoa
 */
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario extends Pessoa {

    @Column
    private String login;
    
    @Column
    private String senha;
    
    @Column
    private boolean ativo;
    
    @Column
    private String cpf;
    
    @Column
    private String documentoSocial;
    
    @Column
    private String email;
}