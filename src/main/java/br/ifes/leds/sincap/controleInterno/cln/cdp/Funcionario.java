package br.ifes.leds.sincap.controleInterno.cln.cdp;

import static com.sun.jmx.snmp.EnumRowStatus.active;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@PrimaryKeyJoinColumn(name = "id")
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