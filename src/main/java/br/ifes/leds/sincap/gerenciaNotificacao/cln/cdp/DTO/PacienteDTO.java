package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    private Long id;
    private String nome;
    private Telefone telefone;
    private Endereco endereco;
    private Calendar dataInternacao;
    private Calendar dataNascimento;
    private String profissao;
    private String nomeMae;
    private String numeroProntuario;
    private String numeroSUS;
    private String nacionalidade;
    private String documentoSocial;
    private Sexo sexo;
    private EstadoCivil EstadoCivil;
}