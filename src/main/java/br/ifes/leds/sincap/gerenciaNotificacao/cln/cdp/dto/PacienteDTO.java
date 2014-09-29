package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.reuse.endereco.cdp.dto.EnderecoDTO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import lombok.experimental.Builder;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;
    private String nome;
    private Telefone telefone;
    private EnderecoDTO endereco;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar dataInternacao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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
