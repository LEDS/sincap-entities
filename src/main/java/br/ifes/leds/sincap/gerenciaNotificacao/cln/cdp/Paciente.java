 package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import lombok.Getter;
import lombok.Setter;


/**
 * Paciente.java
 * @author 20091BSI0273
 * Classe abstrata que representa um paciente
 */
@Setter
@Getter
@Entity
public class Paciente extends Pessoa {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInternacao;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataNascimento;
    
    @Column
    private String profissao;

    @Column
    private String nomeMae;
    
    @Column
    private String numeroProntuario;

    @Column
    private String numeroSUS;
    
    @Column
    private String nacionalidade;
    
    @Column
    private String documentoSocial;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @OneToOne
    private Endereco endereco;

    @Enumerated (EnumType.STRING)
    private TipoPaciente tipoPaciente;// tipo de paciente ex: crianca, adulto, nao-identificado, recem-nasciso
    
    @Enumerated (EnumType.STRING)
    private EstadoCivil EstadoCivil; // tipo de estados civis ex: SOLTEIRO, CASADO, DIVORCIADO, VIUVO

    @OneToOne
    private Doacao doacao;
}
