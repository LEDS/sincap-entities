package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Responsavel.java
 * @author 20091BSI0273
 * Classe que representa uma pessoa responsavel por um paciente
 */
@Getter
@Setter
@Entity
public class Responsavel extends Pessoa {
	
    @Column
    private String nacionalidade;
    
    @Column
    private String profissao;
    
    @Column
    private String documentoSocial;
    
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;
    
    @OneToOne
    private Telefone telefone2;
}
