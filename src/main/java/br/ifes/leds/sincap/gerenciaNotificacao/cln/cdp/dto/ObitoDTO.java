package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.TemDatasPacienteInterface;
import br.ifes.leds.sincap.validacao.annotations.DatasObitoConsistentes;
import lombok.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import lombok.experimental.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@DatasObitoConsistentes
public class ObitoDTO implements TemDatasPacienteInterface {

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
}
