package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import br.ifes.leds.reuse.endereco.cdp.dto.EnderecoDTO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.PacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasPacienteConsistentes;
import lombok.*;
import lombok.experimental.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@DatasPacienteConsistentes
public class PacienteDTO implements PacienteInterface {

    private Long id;

    private String nome;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar dataInternacao;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar dataNascimento;

    @Length(min = 3, max = 255)
    private String profissao;

    private String nomeMae;

    private String religiao;

    @Length(min = 3, max = 255)
    private String numeroProntuario;

    private String numeroSUS;

    @Length(min = 3, max = 255)
    private String nacionalidade;

    private DocumentoComFotoDTO documentoSocial;

    private Sexo sexo;

    private Telefone telefone;
    private EnderecoDTO endereco;
    private EstadoCivil EstadoCivil;
}
