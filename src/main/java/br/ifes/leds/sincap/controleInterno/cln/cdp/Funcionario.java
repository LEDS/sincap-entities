package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.sincap.validacao.annotations.TelefoneFuncionarioConsistentes;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Funcionario
 *
 * @author 20091BSI0273 Classe abstrata que representa um Funcionario, herda de
 *         Pessoa
 */
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@TelefoneFuncionarioConsistentes
public class Funcionario extends Pessoa {

    @Column
    @Size(min = 3, max = 50)
    @NotNull
    private String senha;

    @Column
    @NotNull
    private boolean ativo;

    //Verificar a necessidade desse annotation,
    // visto que há várias restrições que impossibilitam seu uso em desenvolvimento,
    // mas, ajudarah muito na producao.
    //@CPF
    @Column(unique = true)
    @NotNull
    private String cpf;

    @Column
    @Size(min = 3, max = 255)
    @NotNull
    private String documentoSocial;

    @Column
    @Email
    @NotNull
    private String email;

    @OneToMany
    private List<Permissao> permissoes;
}