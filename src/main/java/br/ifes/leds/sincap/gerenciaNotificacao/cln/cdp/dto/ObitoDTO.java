package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ObitoInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;
import lombok.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import lombok.experimental.Builder;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import static org.joda.time.Years.yearsBetween;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@DatasObitoConsistentes
public class ObitoDTO implements ObitoInterface {

    private Long id;

    @Valid
    @NotNull
    private PacienteDTO paciente = new PacienteDTO();

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataObito;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataCadastro;

    @NotNull
    private boolean aptoDoacao;

    @NotNull
    private CausaMortis primeiraCausaMortis;

    @NotNull
    private CausaMortis segundaCausaMortis;

    @NotNull
    private Long setor;

    private Long hospital;
    private CausaMortis terceiraCausaMortis;
    private CausaMortis quartaCausaMortis;
    private TipoObito tipoObito;
    private CorpoEncaminhamento corpoEncaminhamento;

    @Override
    public boolean haCausaNaoDoacao() {
        return !this.isAptoDoacao();
    }

    @Override
    public Integer getIdadePaciente() {
        if (this.paciente == null || this.paciente.getDataNascimento() == null || this.dataObito == null) {
            return null;
        } else {
            DateTime dataNascimento = new DateTime(this.paciente.getDataNascimento());
            DateTime dataObito = new DateTime(this.dataObito);
            return yearsBetween(dataNascimento, dataObito).getYears();
        }
    }
}
