package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

    private Calendar dataInternacao;

    private Calendar dataNascimento;

    private String telefone;

    private String profissao;

    private String nomeMae;

    private String numeroProntuario;

    private String numeroSUS;

    private String nacionalidade;

    private String documentoSocial;

    private Sexo sexo;

    private EstadoCivil EstadoCivil;

    private String logradouro;

    private String numero;

    private String complemento;

    private Bairro bairro;

    private Cidade cidade;

    private Estado estado;

    private String cep;
}
